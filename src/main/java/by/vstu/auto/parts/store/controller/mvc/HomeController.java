package by.vstu.auto.parts.store.controller.mvc;

import by.vstu.auto.parts.store.sevice.CategoryService;
import by.vstu.auto.parts.store.sevice.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final PartService partService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categoryCount", categoryService.getCategoriesByPage(PageRequest.of(0, 1)).getTotalElements());
        model.addAttribute("brandCount", partService.getBrandsByPage(PageRequest.of(0, 1)).getTotalElements());
        model.addAttribute("partCount", partService.getPartsByPage(PageRequest.of(0, 1)).getTotalElements());
        model.addAttribute("recentParts", partService.getPartsByPage(PageRequest.of(0, 5)).getContent());
        return "index";
    }
}
