package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Данные для создания категории")
public record CategoryCreateRequestDto(
        @NotBlank @Schema(description = "Название категории", example = "Тормозная система") String name
) {
}
