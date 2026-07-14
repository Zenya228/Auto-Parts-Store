package by.vstu.auto.parts.store.controller.mvc;

import by.vstu.auto.parts.store.dto.request.BrandCreateRequestDto;
import by.vstu.auto.parts.store.exception.common.ResourceNotFoundException;
import by.vstu.auto.parts.store.sevice.PartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private static final int PAGE_SIZE = 12;

    private final PartService partService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<?> brands = partService.getBrandsByPage(PageRequest.of(page, PAGE_SIZE));
        model.addAttribute("brands", brands);
        return "brands/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        if (!model.containsAttribute("brand")) {
            model.addAttribute("brand", new BrandCreateRequestDto(null));
        }
        return "brands/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("brand") BrandCreateRequestDto requestDto,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "brands/form";
        }

        partService.createBrand(requestDto);
        redirectAttributes.addFlashAttribute("success", "Бренд успешно создан");
        return "redirect:/brands";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            partService.deleteBrand(id);
            redirectAttributes.addFlashAttribute("success", "Бренд успешно удалён");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/brands";
    }
}
