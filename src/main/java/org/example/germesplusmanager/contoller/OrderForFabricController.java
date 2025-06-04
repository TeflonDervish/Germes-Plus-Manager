package org.example.germesplusmanager.contoller;


import lombok.AllArgsConstructor;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.Fabric;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointForFabric;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointOfSale;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order/fabric")
@AllArgsConstructor
public class OrderForFabricController {

    private final ProductForIndividualService productForIndividualService;
    private final PointOfSaleService pointService;
    private final FabricService fabricService;
    private final KorzinaOnPointForFabricService korzinaOnPointForFabricService;
    private final OrderForFabricService orderForFabricService;

    @ModelAttribute
    public void addAttributes(
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        model.addAttribute("products", productForIndividualService.getAll());
        model.addAttribute("manager", manager);

        model.addAttribute("status",
                Arrays.stream(OrderStatus.values())
                        .filter(status -> status != OrderStatus.WAITING_ACCESS)
                        .toArray(OrderStatus[]::new));
    }

    @GetMapping("/create")
    public String createFabric(
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {

        List<ProductForIndividual> products = productForIndividualService.getAll();
        List<PointOfSale> points = pointService.getAll();
        List<Fabric> fabrics = fabricService.getAll();
        KorzinaOnPointForFabric korzina = korzinaOnPointForFabricService.getKorzina(manager);

        model.addAttribute("products", products);
        model.addAttribute("points", points);
        model.addAttribute("korzina", korzina);
        model.addAttribute("fabrics", fabrics);

        return "forGlavMan/create_order_for_fabric";
    }

    @PostMapping("/create")
    public String createOrder(
            @AuthenticationPrincipal PointManager manager,
            @RequestParam Long fabricId
    ) {
        orderForFabricService.createOrder(fabricId, manager);
        return "redirect:/sclad";
    }

}
