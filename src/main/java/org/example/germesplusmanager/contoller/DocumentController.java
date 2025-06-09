package org.example.germesplusmanager.contoller;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.othcet.OtchetForPoint;
import org.example.germesplusmanager.service.OrderForIndividualService;
import org.example.germesplusmanager.service.OtchetForPointService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;


@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class DocumentController {

    private static final Log log = LogFactory.getLog(DocumentController.class);
    private final OtchetForPointService otchetForPointService;
    private final OrderForIndividualService orderForIndividualService;

    // Общие заголовки для файлов
    private HttpHeaders prepareHeaders(String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        return headers;
    }

    @GetMapping("/otchet/{id}")
    public ResponseEntity<InputStreamResource> generateCsv(
            @PathVariable Long id
    ) throws IOException {

        log.info("Создание отчета");
        // Создаем CSV в памяти
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);
        OtchetForPoint othcet = otchetForPointService.getById(id);

        csvWriter.writeNext(new String[]{
                "Статус",
                "Номер заказа",
                "Дата заказа",
                "Стоимость заказа",
                "Магазин"});

        for (OrderForIndividual order : othcet.getOrders()) {
            csvWriter.writeNext(new String[]{order.getStatus().getTitle(),
                    order.getId().toString(),
                    order.getOrderDate().toString(),
                    order.getTotalPrice().toString(),
                    order.getPointOfSale().getName()});
        }

        csvWriter.writeNext(new String[]{});

        csvWriter.writeNext(new String[] {"Наименование", othcet.getName()});
        csvWriter.writeNext(new String[] {"От", othcet.getName()});
        csvWriter.writeNext(new String[] {"До", othcet.getName()});
        csvWriter.writeNext(new String[] {"Сумма", othcet.getTotalPrice().toString()});
        csvWriter.writeNext(new String[] {"Кол-во диванов", othcet.getCount().toString()});
        csvWriter.writeNext(new String[] {"Средняя стоимость", othcet.getMeanPrice().toString()});

        ByteArrayInputStream in = new ByteArrayInputStream(writer.toString().getBytes());

        return ResponseEntity.ok()
                .headers(prepareHeaders(othcet.getName() + ".csv"))
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(in));

    }

    @GetMapping("/order/{id}")
    public ResponseEntity<InputStreamResource> generateDocx(
            @PathVariable Long id
    ) throws Exception {

        log.info("Создание заказа");
        // Создаем CSV в памяти
        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart documentPart = wordPackage.getMainDocumentPart();
        OrderForIndividual order = orderForIndividualService.getById(id);

        documentPart.addStyledParagraphOfText("Heading2", "Детали заказа:");
        documentPart.addParagraphOfText("Номер заказа № " + order.getId());
        documentPart.addParagraphOfText("Дата: " + order.getOrderDate());

        documentPart.addStyledParagraphOfText("Heading2", "Товары:");
        addTableToDocument(documentPart, order);

        documentPart.addParagraphOfText("Итого: " + order.getTotalPrice().toString());


        documentPart.addStyledParagraphOfText("Heading2", "Данные заказчика:");
        documentPart.addParagraphOfText("Ф.И.: " + order.getIndividualPerson().getSurname() +
                " " + order.getIndividualPerson().getName());
        documentPart.addParagraphOfText("Телефон: " + order.getIndividualPerson().getPhone());
        documentPart.addParagraphOfText("Email: " + order.getIndividualPerson().getEmail());

        documentPart.addStyledParagraphOfText("Heading2", "Данные фабрики:");
        documentPart.addParagraphOfText("Продавец: " + order.getPointOfSale().getName());
        documentPart.addParagraphOfText("Ответственный менеджер: " + order.getPointManager().getName());
        documentPart.addParagraphOfText("Телефон: " + order.getPointOfSale().getPhoneNumber());
        documentPart.addParagraphOfText("Email: " + order.getPointOfSale().getEmail());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wordPackage.save(out);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        return ResponseEntity.ok()
                .headers(prepareHeaders("Заказ_№_" + order.getId() + ".docx"))
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(new InputStreamResource(in));
    }

    private void addTableToDocument(MainDocumentPart documentPart, OrderForIndividual order) throws Exception {
        // Создаем таблицу 4x4 (rows x cols)
        int rows = order.getProducts().size() + 1;
        int cols = 4;

        Tbl table = ObjectFactory.get().createTbl();
        TblPr tblPr = ObjectFactory.get().createTblPr();
        table.setTblPr(tblPr);

        // Добавляем строки
        for (int row = 0; row < rows; row++) {
            Tr tr = ObjectFactory.get().createTr();

            for (int col = 0; col < cols; col++) {
                Tc tc = ObjectFactory.get().createTc();
                P p = ObjectFactory.get().createP();

                if (row == 0) {
                    if (col == 0) {
                        p.getContent().add(createTextRun("Наименование", true));
                    } else if (col == 1) {
                        p.getContent().add(createTextRun("Габариты", true));
                    } else if (col == 2) {
                        p.getContent().add(createTextRun("Кол-во", true));
                    } else if (col == 3) {
                        p.getContent().add(createTextRun("Цена", true));
                    }
                } else {
                    // Данные таблицы
                    if (col == 0) {
                        p.getContent().add(createTextRun(order.getProducts().get(row - 1).getName(), false));
                    } else if (col == 1) {
                        p.getContent().add(createTextRun(order.getProducts().get(row - 1).getSize(), false));
                    } else if (col == 2) {
                        p.getContent().add(createTextRun("1", false));
                    } else if (col == 3) {
                        p.getContent().add(createTextRun(order.getProducts().get(row - 1).getPrice().toString() + " руб.", false));
                    }
                }

                tc.getContent().add(p);
                tr.getContent().add(tc);
            }

            table.getContent().add(tr);
        }

        documentPart.getContent().add(table);
    }

    private org.docx4j.wml.R createTextRun(String text, boolean bold) {
        org.docx4j.wml.ObjectFactory factory = new org.docx4j.wml.ObjectFactory();
        org.docx4j.wml.R run = factory.createR();
        org.docx4j.wml.Text t = factory.createText();
        t.setValue(text);

        if (bold) {
            org.docx4j.wml.RPr rpr = factory.createRPr();
            org.docx4j.wml.BooleanDefaultTrue b = factory.createBooleanDefaultTrue();
            rpr.setB(b);
            run.setRPr(rpr);
        }

        run.getContent().add(t);
        return run;
    }

}
