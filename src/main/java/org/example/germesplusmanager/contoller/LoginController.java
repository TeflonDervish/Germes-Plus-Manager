package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.dto.IndividualPersonRegisterDto;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.service.IndividualPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {

    private final IndividualPersonService individualPersonService;

    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal PointManager manager) {
        model.addAttribute("manager", manager);
    }

    @GetMapping("/login")
    public String login() {
        return "login_for_point";
    }

    @GetMapping("/registration")
    public String registration() {
        return "client_registration";
    }

    @PostMapping("/registration/client")
    public String registerClient(@ModelAttribute IndividualPerson individualPerson) {
        individualPersonService.registerIndividualPerson(individualPerson);
        return "client_registration";
    }
}
