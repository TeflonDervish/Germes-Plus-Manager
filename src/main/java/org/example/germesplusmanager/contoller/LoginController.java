package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.service.IndividualPersonService;
import org.example.germesplusmanager.service.PointManagerService;
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
    private final PointManagerService pointManagerService;

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
    public String registration(
            @AuthenticationPrincipal PointManager manager
    ) {
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/client_registration";
        return "client_registration";
    }

    @GetMapping("/registration/manager")
    public String registrationManager(
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/registration_manager";
        return "redirect:/registration";
    }

    @PostMapping("/registration/manager")
    public String registrationManager(
            Model model,
            @AuthenticationPrincipal PointManager manager,
            @ModelAttribute PointManager reg

    ) {
        pointManagerService.createManager(reg, manager);
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/registration_manager";
        return "redirect:/registration";
    }

    @PostMapping("/registration/client")
    public String registerClient(
            @ModelAttribute IndividualPerson individualPerson
    ) {
        individualPersonService.registerIndividualPerson(individualPerson);
        return "client_registration";
    }

//    @PostMapping("registration/")
}
