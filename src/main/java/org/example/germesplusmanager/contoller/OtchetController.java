package org.example.germesplusmanager.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/otchet")
public class OtchetController {

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
}
