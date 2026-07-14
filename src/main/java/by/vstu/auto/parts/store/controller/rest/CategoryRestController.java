package by.vstu.auto.parts.store.controller.rest;

import by.vstu.auto.parts.store.dto.request.CategoryCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.CategoryEditRequestDto;
import by.vstu.auto.parts.store.dto.response.CategoryInfoResponseDto;
import by.vstu.auto.parts.store.dto.response.ErrorInfoResponseDto;
import by.vstu.auto.parts.store.sevice.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Категории", description = "Управление категориями запчастей")
@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @Operation(summary = "Получить категории постранично", description = "Возвращает страницу категорий с заданным номером и размером страницы")
    @ApiResponse(responseCode = "200", description = "Страница категорий успешно получена")
    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<CategoryInfoResponseDto> getCategoriesByPage(
            @Parameter(description = "Номер страницы, начиная с 0") @PathVariable int pageNumber,
            @Parameter(description = "Количество элементов на странице") @PathVariable int pageSize) {
        return categoryService.getCategoriesByPage(PageRequest.of(pageNumber, pageSize));
    }

    @Operation(summary = "Получить категорию по id", description = "Возвращает категорию по её id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Категория найдена"),
            @ApiResponse(responseCode = "404", description = "Категория с указанным id не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorInfoResponseDto.class)))
    })
    @GetMapping("/{id}")
    public CategoryInfoResponseDto getById(@Parameter(description = "Id категории") @PathVariable Long id) {
        return categoryService.getById(id);
    }

    @Operation(summary = "Создать категорию", description = "Создаёт новую категорию запчастей")
    @ApiResponse(responseCode = "200", description = "Категория успешно создана")
    @PostMapping
    public CategoryInfoResponseDto create(@Valid @RequestBody CategoryCreateRequestDto requestDto) {
        return categoryService.create(requestDto);
    }

    @Operation(summary = "Редактировать категорию", description = "Изменяет данные существующей категории по её id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Категория успешно изменена"),
            @ApiResponse(responseCode = "404", description = "Категория с указанным id не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorInfoResponseDto.class)))
    })
    @PutMapping
    public CategoryInfoResponseDto edit(@Valid @RequestBody CategoryEditRequestDto requestDto) {
        return categoryService.edit(requestDto);
    }

    @Operation(summary = "Удалить категорию", description = "Удаляет категорию по её id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Категория успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Категория с указанным id не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorInfoResponseDto.class)))
    })
    @DeleteMapping("/{id}")
    public CategoryInfoResponseDto delete(@Parameter(description = "Id категории") @PathVariable Long id) {
        return categoryService.delete(id);
    }
}
