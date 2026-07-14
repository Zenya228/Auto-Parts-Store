package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для редактирования категории")
public record CategoryEditRequestDto(
        @Schema(description = "Id редактируемой категории", example = "1") Long id,
        @Schema(description = "Новое название категории", example = "Тормозная система") String name
) {
}
