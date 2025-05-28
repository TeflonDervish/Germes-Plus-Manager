package org.example.germesplusmanager.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login_for_point";
    }

    @GetMapping("/registration")
    public String registration() {
        return "client_registration";
    }
}
