package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.repository.OrderForIndividualRepository;
import org.example.germesplusmanager.service.OrderForIndividualService;
import org.example.germesplusmanager.service.PointOfSaleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private static final Log log = LogFactory.getLog(OrderController.class);
    private final OrderForIndividualService orderForIndividualService;
    private final PointOfSaleService pointOfSaleService;

    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal PointManager manager) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String orders(Model model) {
        List<OrderForIndividual> orders = orderForIndividualService.getAll();

        model.addAttribute("orders", orders);

        return "orders";
    }

    @GetMapping("/{id}")
    public String order(
            @PathVariable Long id,
            Model model) {
        OrderForIndividual order = orderForIndividualService.getById(id);
        List<ProductForIndividual> products = order.getProducts();


        model.addAttribute("products", products);
        model.addAttribute("order", order);
        model.addAttribute("status", OrderStatus.values());

        return "order";
    }

    @GetMapping("/create")
    public String create(
            Model model
    ) {
        List<PointOfSale> points = pointOfSaleService.getAll();

        model.addAttribute("points", points);

        return "create_order";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        List<OrderForIndividual> orders = List.of(orderForIndividualService.getById(Long.parseLong(query)));
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/change-status/{id}")
    public String changeStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        log.info(status);
        orderForIndividualService.changeOrderStatus(id, OrderStatus.valueOf(status));
        return "redirect:/order/" + id;
    }
}
