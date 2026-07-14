package by.vstu.auto.parts.store.controller.rest;

import by.vstu.auto.parts.store.dto.request.BrandCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.response.BrandInfoResponseDto;
import by.vstu.auto.parts.store.dto.response.ErrorInfoResponseDto;
import by.vstu.auto.parts.store.dto.response.PartInfoResponseDto;
import by.vstu.auto.parts.store.sevice.PartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Запчасти и бренды", description = "Управление запчастями и брендами")
@RestController
@RequestMapping("api/v1/parts")
@RequiredArgsConstructor
public class PartRestController {

    private final PartService partService;

    // PARTS

    @Operation(summary = "Получить запчасти постранично", description = "Возвращает страницу запчастей с заданным номером и размером страницы")
    @ApiResponse(responseCode = "200", description = "Страница запчастей успешно получена")
    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<PartInfoResponseDto> getPartsByPage(
            @Parameter(description = "Номер страницы, начиная с 0") @PathVariable int pageNumber,
            @Parameter(description = "Количество элементов на странице") @PathVariable int pageSize) {
        return partService.getPartsByPage(PageRequest.of(pageNumber, pageSize));
    }

    @Operation(summary = "Создать запчасть", description = "Создаёт новую запчасть с изображением, привязанную к категории и бренду")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запчасть успешно создана"),
            @ApiResponse(responseCode = "404", description = "Указанная категория или бренд не найдены",
                    content = @Content(schema = @Schema(implementation = ErrorInfoResponseDto.class)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PartInfoResponseDto create(@Valid @ModelAttribute PartCreateRequestDto requestDto) {
        return partService.create(requestDto);
    }

    @Operation(summary = "Редактировать запчасть", description = "Изменяет данные существующей запчасти по её id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запчасть успешно изменена"),
            @ApiResponse(responseCode = "404", description = "Запчасть с указанным id не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorInfoResponseDto.class)))
    })
    @PutMapping
    public PartInfoResponseDto edit(@Valid @RequestBody PartEditRequestDto requestDto) {
        return partService.edit(requestDto);
    }

    @Operation(summary = "Удалить запчасть", description = "Удаляет запчасть по её id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запчасть успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Запчасть с указанным id не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorInfoResponseDto.class)))
    })
    @DeleteMapping("/{id}")
    public PartInfoResponseDto delete(@Parameter(description = "Id запчасти") @PathVariable Long id) {
        return partService.delete(id);
    }

    // BRANDS

    @Operation(summary = "Получить бренды постранично", description = "Возвращает страницу брендов с заданным номером и размером страницы")
    @ApiResponse(responseCode = "200", description = "Страница брендов успешно получена")
    @GetMapping("/brands/{pageNumber}/{pageSize}")
    public Page<BrandInfoResponseDto> getBrandsByPage(
            @Parameter(description = "Номер страницы, начиная с 0") @PathVariable int pageNumber,
            @Parameter(description = "Количество элементов на странице") @PathVariable int pageSize) {
        return partService.getBrandsByPage(PageRequest.of(pageNumber, pageSize));
    }

    @Operation(summary = "Создать бренд", description = "Создаёт новый бренд")
    @ApiResponse(responseCode = "200", description = "Бренд успешно создан")
    @PostMapping("/brands")
    public BrandInfoResponseDto createBrand(@Valid @RequestBody BrandCreateRequestDto requestDto) {
        return partService.createBrand(requestDto);
    }

    @Operation(summary = "Удалить бренд", description = "Удаляет бренд по его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Бренд успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Бренд с указанным id не найден",
                    content = @Content(schema = @Schema(implementation = ErrorInfoResponseDto.class)))
    })
    @DeleteMapping("/brands/{id}")
    public BrandInfoResponseDto deleteBrand(@Parameter(description = "Id бренда") @PathVariable Long id) {
        return partService.deleteBrand(id);
    }
}
