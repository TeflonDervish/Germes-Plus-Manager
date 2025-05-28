package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.service.PointOfSaleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class PointController {

    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal PointManager manager) {
        model.addAttribute("manager", manager);
    }

    @GetMapping("/point")
    public String getPointInfo(Model model,
                               @AuthenticationPrincipal PointManager pointManager) {

        PointOfSale point = pointManager.getPointOfSale();

        model.addAttribute("name", point.getName());
        model.addAttribute("address", point.getAddress());
        model.addAttribute("point_on_the_map", point.getPointOnTheMap());
        model.addAttribute("phone_number", point.getPhoneNumber());
        model.addAttribute("email", point.getEmail());

        return "point_info";
    }
}
