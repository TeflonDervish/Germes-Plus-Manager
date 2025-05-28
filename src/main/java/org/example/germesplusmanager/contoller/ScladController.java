package org.example.germesplusmanager.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sclad")
public class ScladController {

    @GetMapping
    public String sclad(Model model) {
        return "sclad";
    }
}
