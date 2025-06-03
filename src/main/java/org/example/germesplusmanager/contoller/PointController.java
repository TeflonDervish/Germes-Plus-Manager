package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.feedbacks.FeedbackOnPoint;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.service.FeedbackOnPointService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@AllArgsConstructor
public class PointController {

    private final FeedbackOnPointService feedbackOnPointService;

    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal PointManager manager
    ) {
        model.addAttribute("manager", manager);
    }

    @GetMapping("/point")
    public String getPointInfo(Model model,
                               @AuthenticationPrincipal PointManager manager
    ) {
        List<FeedbackOnPoint> feedbacks = feedbackOnPointService.getByPointOfSale(manager.getPointOfSale().getId());
        model.addAttribute("feedbacks", feedbacks);
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/point_info";
        return "point_info";
    }
}
