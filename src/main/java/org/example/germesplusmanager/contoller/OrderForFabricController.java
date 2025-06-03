package org.example.germesplusmanager.contoller;


import lombok.AllArgsConstructor;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointOfSale;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.service.PointOfSaleService;
import org.example.germesplusmanager.service.ProductForIndividualService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderForFabricController {

    private final ProductForIndividualService productForIndividualService;
//    private final FabricService pointOfSaleService;


    @GetMapping("/create/fabric")
    public String createFabric(Model model) {

//        List<Fabro>
//        List<ProductForIndividual> products = productForIndividualService.getAll();
//        KorzinaOnPointOfSale korzina = korzinaOnPointService.getKorzina(manager);

//        model.addAttribute("points", points);
//        model.addAttribute("products", products);
//        model.addAttribute("korzina", korzina);
//        model.addAttribute("totalPrice", korzina.getTotalPrice());
        return "forGlavMan/create_order_for_fabric";
    }

}
