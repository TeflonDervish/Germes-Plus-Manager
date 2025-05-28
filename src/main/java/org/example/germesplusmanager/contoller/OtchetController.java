package org.example.germesplusmanager.contoller;

import org.example.germesplusmanager.model.persons.PointManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/otchet")
public class OtchetController {


    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal PointManager manager) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String index(Model model) {
        return "otchet";
    }

    @GetMapping("/create")
    public String createOtchet() {
        return "create_otchet";
    }

    @GetMapping("/{id}")
    public String otchet(Model model, @PathVariable Long id) {
        return "otchet_form";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        return "otchet";
    }
}
