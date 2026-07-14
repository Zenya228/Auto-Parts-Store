package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания категории")
public record CategoryCreateRequestDto(
        @Schema(description = "Название категории", example = "Тормозная система") String name
) {
}
