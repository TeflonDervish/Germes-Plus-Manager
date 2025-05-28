package org.example.germesplusmanager.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {

    @GetMapping
    public String orders(Model model) {
        return "orders";
    }

    @GetMapping("/{id}")
    public String order(@PathVariable Long id, Model model) {
        return "order";
    }
}
