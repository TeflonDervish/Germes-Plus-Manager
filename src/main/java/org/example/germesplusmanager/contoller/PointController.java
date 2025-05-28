package org.example.germesplusmanager.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PointController {

    @GetMapping("/point")
    public String getPointInfo(Model model) {
        return "point_info";
    }
}
