package org.example.germesplusmanager.contoller;


import lombok.AllArgsConstructor;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointForFabric;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.service.KorzinaOnPointForFabricService;
import org.example.germesplusmanager.service.KorzinaOnPointService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/korzina")
@AllArgsConstructor
public class KorzinaController {

    private final KorzinaOnPointService korzinaOnPointService;
    private final KorzinaOnPointForFabricService korzinaOnPointForFabricService;

    @PostMapping("/add")
    public String addProduct(
            @RequestParam Long productId,
            @AuthenticationPrincipal PointManager pointManager
    ) {
        korzinaOnPointService.addProduct(productId, pointManager);
        return "redirect:/order/create";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(
            @AuthenticationPrincipal PointManager pointManager,
            @PathVariable Long id
    ) {
        korzinaOnPointService.deleteProduct(id, pointManager);
        return "redirect:/order/create";
    }
    @PostMapping("/fabric/add")
    public String addFabric(
            @RequestParam Long productId,
            @AuthenticationPrincipal PointManager pointManager
    ) {
        korzinaOnPointForFabricService.addProduct(productId, pointManager);
        return "redirect:/order/fabric/create";
    }

    @PostMapping("/fabric/delete/{id}")
    public String deleteFabric(
            @AuthenticationPrincipal PointManager pointManager,
            @PathVariable Long id
    ) {
        korzinaOnPointForFabricService.deleteProduct(id, pointManager);
        return "redirect:/order/fabric/create";
    }
}
