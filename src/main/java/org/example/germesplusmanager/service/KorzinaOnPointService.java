package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointOfSale;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.repository.KorzinaOnPointOfSaleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class KorzinaOnPointService {

    private static final Log log = LogFactory.getLog(KorzinaOnPointService.class);
    private KorzinaOnPointOfSaleRepository korzinaOnPointOfSaleRepository;
    private ProductForIndividualService productService;

    public KorzinaOnPointOfSale getKorzina(PointManager pointManager) {
        KorzinaOnPointOfSale korzinaForIndividual = korzinaOnPointOfSaleRepository.findByPointManager(pointManager);

        if (korzinaForIndividual == null)
            korzinaForIndividual = createKorzina(pointManager);

        return korzinaForIndividual;
    }

    public KorzinaOnPointOfSale createKorzina(PointManager pointManager) {
        log.info("Создание корзины для пользователя");
        KorzinaOnPointOfSale korzina = new KorzinaOnPointOfSale();
        korzina.setPointManager(pointManager);
        korzina.setProducts(new ArrayList<>());
        return korzinaOnPointOfSaleRepository.save(korzina);
    }

    public KorzinaOnPointOfSale addProduct(Long productId, PointManager pointManager) {
        log.info("Добавление продукта в корзину");
        ProductForIndividual product = productService.getById(productId);
        KorzinaOnPointOfSale korzina = getKorzina(pointManager);
        korzina.addProduct(product);
        return korzinaOnPointOfSaleRepository.save(korzina);
    }

    public KorzinaOnPointOfSale deleteProduct(Long productId, PointManager pointManager) {
        log.info("Удаление продукта из корзины");
        ProductForIndividual product = productService.getById(productId);
        KorzinaOnPointOfSale korzina = getKorzina(pointManager);
        korzina.deleteProduct(product);
        return korzinaOnPointOfSaleRepository.save(korzina);
    }

    public boolean isInKorzina(Long productId, PointManager pointManager) {
        log.info("Проверка, находится ли продукт в корзине");
        ProductForIndividual product =  productService.getById(productId);
        KorzinaOnPointOfSale korzina = getKorzina(pointManager);
        return korzina.isInKorzina(product);
    }

    public KorzinaOnPointOfSale clear(PointManager pointManager) {
        KorzinaOnPointOfSale korzina = getKorzina(pointManager);
        korzina.getProducts().clear();
        return korzinaOnPointOfSaleRepository.save(korzina);
    }
}
