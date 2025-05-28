package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.model.Sclad;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.service.ScladService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sclad")
@AllArgsConstructor
public class ScladController {

    private ScladService scladService;

    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal PointManager manager) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String sclad(Model model,
                        @AuthenticationPrincipal PointManager manager) {
        List<Sclad> sclad = scladService.getByPointOfSale(manager.getPointOfSale());

        model.addAttribute("scladList", sclad);

        return "sclad";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        List<Sclad> sclads = scladService.getByProductName(query);

        model.addAttribute("scladList", sclads);

        return "sclad";
    }
}
