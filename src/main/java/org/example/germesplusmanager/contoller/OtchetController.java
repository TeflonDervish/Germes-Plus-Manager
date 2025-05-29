package org.example.germesplusmanager.contoller;

import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.persons.PointManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/otchet")
public class OtchetController {


    @ModelAttribute
    public void addAttributes(
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String index(
            @AuthenticationPrincipal PointManager manager
    ) {
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/otchet";
        return "otchet";
    }

    @GetMapping("/create")
    public String createOtchet(
            @AuthenticationPrincipal PointManager manager
    ) {
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/create_otchet";
        return "create_otchet";
    }

    @GetMapping("/{id}")
    public String otchet(Model model,
                         @PathVariable Long id,
                         @AuthenticationPrincipal PointManager manager
    ) {
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/otchet_form";
        return "otchet_form";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam String query,
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/otchet";
        return "otchet";
    }
}
