package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.enums.DeliveryType;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.service.OrderForIndividualService;
import org.example.germesplusmanager.service.PointOfSaleService;
import org.example.germesplusmanager.service.ProductForIndividualService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private static final Log log = LogFactory.getLog(OrderController.class);
    private final OrderForIndividualService orderForIndividualService;
    private final PointOfSaleService pointOfSaleService;
    private final ProductForIndividualService productForIndividualService;

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

    @GetMapping
    public String orders(
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        List<OrderForIndividual> orders = orderForIndividualService.getByPointOfSale(manager.getPointOfSale());

        for (OrderForIndividual order : orderForIndividualService.getByDeliveryType(DeliveryType.DELIVERY)) {
            if (!orders.contains(order)) {
                orders.add(order);
            }
        }

        model.addAttribute("orders", orders);
        model.addAttribute("deliveryType", DeliveryType.values());

        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/orders";
        return "orders";
    }

    @GetMapping("/{id}")
    public String order(
            @PathVariable Long id,
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        OrderForIndividual order = orderForIndividualService.getById(id);
        List<ProductForIndividual> products = order.getProducts();


        model.addAttribute("products", products);
        model.addAttribute("order", order);

        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/order";
        return "order";
    }

    @GetMapping("/create")
    public String create(
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        List<PointOfSale> points = pointOfSaleService.getAll();

        model.addAttribute("points", points);

        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/create_order";
        return "create_order";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam String query,
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        List<OrderForIndividual> orders = List.of(orderForIndividualService.getById(Long.parseLong(query)));

        model.addAttribute("orders", orders);

        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/orders";
        return "orders";
    }

    @PostMapping("/change-status/{id}")
    public String changeStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @AuthenticationPrincipal PointManager manager
    ) {
        orderForIndividualService.changeOrderStatus(id, OrderStatus.valueOf(status), manager);
        return "redirect:/order/" + id;
    }

    @PostMapping("/status")
    public String findByStatus(
            Model model,
            @RequestParam OrderStatus status,
            @AuthenticationPrincipal PointManager manager
    ) {
        List<OrderForIndividual> orders = orderForIndividualService.getByStatus(status);

        model.addAttribute("orders", orders);

        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/orders";
        return "orders";
    }

    @PostMapping("/delivery")
    public String getDeliveryType(
            Model model,
            @RequestParam String deliveryType,
            @AuthenticationPrincipal PointManager manager
    ) {
        if (deliveryType.equals("null")) return "redirect:/order";

        List<OrderForIndividual> orders = orderForIndividualService.getByDeliveryType(DeliveryType.valueOf(deliveryType));

        model.addAttribute("orders", orders);

        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/orders";
        return "orders";
    }
}
