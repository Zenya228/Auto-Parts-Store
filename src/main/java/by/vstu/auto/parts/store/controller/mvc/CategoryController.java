package by.vstu.auto.parts.store.controller.mvc;

import by.vstu.auto.parts.store.dto.request.CategoryCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.CategoryEditRequestDto;
import by.vstu.auto.parts.store.exception.common.ResourceNotFoundException;
import by.vstu.auto.parts.store.sevice.CategoryService;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private static final int PAGE_SIZE = 8;

    private final CategoryService categoryService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<?> categories = categoryService.getCategoriesByPage(PageRequest.of(page, PAGE_SIZE));
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", new CategoryCreateRequestDto(null));
        }
        return "categories/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("category") CategoryCreateRequestDto requestDto,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "categories/form";
        }

        categoryService.create(requestDto);
        redirectAttributes.addFlashAttribute("success", "Категория успешно создана");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            var category = categoryService.getById(id);
            model.addAttribute("category", new CategoryEditRequestDto(category.id(), category.name()));
            return "categories/edit";
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/categories";
        }
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable Long id, @Valid @ModelAttribute("category") CategoryEditRequestDto requestDto,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "categories/edit";
        }

        try {
            categoryService.edit(requestDto);
            redirectAttributes.addFlashAttribute("success", "Категория успешно изменена");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Категория успешно удалена");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categories";
    }
}
