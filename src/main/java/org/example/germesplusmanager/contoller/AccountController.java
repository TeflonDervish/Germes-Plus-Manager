package org.example.germesplusmanager.contoller;


import org.example.germesplusmanager.model.persons.PointManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal PointManager manager) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String account(Model model) {
        return "account";
    }
}
