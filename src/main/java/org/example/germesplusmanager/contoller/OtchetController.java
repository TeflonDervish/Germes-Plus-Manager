package org.example.germesplusmanager.contoller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.dto.OtchetDto;
import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.othcet.OtchetForPoint;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.service.OtchetForPointService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/otchet")
@AllArgsConstructor
public class OtchetController {

    private static final Log log = LogFactory.getLog(OtchetController.class);
    private final OtchetForPointService otchetForPointService;

    @ModelAttribute
    public void addAttributes(
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        model.addAttribute("manager", manager);
    }

    @GetMapping
    public String index(
            @AuthenticationPrincipal PointManager manager,
            Model model
    ) {
        List<OtchetForPoint> otchets = otchetForPointService.getByPointOfSale(manager.getPointOfSale());
        model.addAttribute("otchets", otchets);
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
        OtchetForPoint otchet = otchetForPointService.getById(id);
        model.addAttribute("otchet", otchet);
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/otchet_form";
        return "otchet_form";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam String query,
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        List<OtchetForPoint> otchets = otchetForPointService.getByPointOfSaleAndNameContains(manager.getPointOfSale(), query);
        model.addAttribute("otchets", otchets);
        if (manager.getRole().equals(Role.ADMIN)) return "forGlavMan/otchet";
        return "otchet";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute OtchetDto otchetDto,
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        otchetForPointService.createOtchet(manager, otchetDto);
        return "redirect:/otchet";
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            Model model,
            @AuthenticationPrincipal PointManager manager
    ) {
        log.info("Удаление отчета");
        otchetForPointService.deleteById(id);
        return "redirect:/otchet";
    }
}
