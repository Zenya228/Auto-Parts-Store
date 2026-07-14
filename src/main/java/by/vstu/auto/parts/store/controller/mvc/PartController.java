package by.vstu.auto.parts.store.controller.mvc;

import by.vstu.auto.parts.store.dto.request.PartCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.request.PartFilterRequestDto;
import by.vstu.auto.parts.store.dto.response.BrandInfoResponseDto;
import by.vstu.auto.parts.store.dto.response.CategoryInfoResponseDto;
import by.vstu.auto.parts.store.exception.common.ResourceNotFoundException;
import by.vstu.auto.parts.store.sevice.CategoryService;
import by.vstu.auto.parts.store.sevice.PartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parts")
@RequiredArgsConstructor
public class PartController {

    private static final int PAGE_SIZE = 9;

    private final PartService partService;
    private final CategoryService categoryService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(required = false) Long categoryId,
                        @RequestParam(required = false) Long brandId,
                        @RequestParam(required = false) BigDecimal minPrice,
                        @RequestParam(required = false) BigDecimal maxPrice,
                        @RequestParam(required = false) Boolean inStockOnly,
                        Model model) {
        PartFilterRequestDto filter = new PartFilterRequestDto(categoryId, brandId, minPrice, maxPrice, inStockOnly);

        Page<?> parts = partService.getPartsByPage(filter, PageRequest.of(page, PAGE_SIZE));
        model.addAttribute("parts", parts);
        model.addAttribute("filter", filter);

        List<CategoryInfoResponseDto> categories = categoryService.getCategoriesByPage(Pageable.unpaged()).getContent();
        List<BrandInfoResponseDto> brands = partService.getBrandsByPage(Pageable.unpaged()).getContent();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("categoryNames", categories.stream().collect(Collectors.toMap(c -> c.id(), c -> c.name())));
        model.addAttribute("brandNames", brands.stream().collect(Collectors.toMap(b -> b.id(), b -> b.name())));

        return "parts/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        if (!model.containsAttribute("part")) {
            model.addAttribute("part", new PartCreateRequestDto(null, null, null, null, null, null));
        }
        addSelectOptions(model);
        return "parts/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("part") PartCreateRequestDto requestDto,
                          BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addSelectOptions(model);
            return "parts/form";
        }

        try {
            partService.create(requestDto);
            redirectAttributes.addFlashAttribute("success", "Запчасть успешно создана");
            return "redirect:/parts";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            addSelectOptions(model);
            return "parts/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            var part = partService.getById(id);
            model.addAttribute("part", new PartEditRequestDto(part.id(), part.name(), part.price(), part.stock()));
            return "parts/edit";
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/parts";
        }
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable Long id, @Valid @ModelAttribute("part") PartEditRequestDto requestDto,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "parts/edit";
        }

        try {
            partService.edit(requestDto);
            redirectAttributes.addFlashAttribute("success", "Запчасть успешно изменена");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/parts";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            partService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Запчасть успешно удалена");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/parts";
    }

    @GetMapping("/{id}/image")
    @ResponseBody
    public ResponseEntity<byte[]> image(@PathVariable Long id) {
        byte[] image = partService.getImage(id);
        if (image == null || image.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "max-age=3600")
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    private void addSelectOptions(Model model) {
        Pageable unpaged = Pageable.unpaged();
        model.addAttribute("categories", categoryService.getCategoriesByPage(unpaged).getContent());
        model.addAttribute("brands", partService.getBrandsByPage(unpaged).getContent());
    }
}
