package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Данные для редактирования категории")
public record CategoryEditRequestDto(
        @NotNull @Schema(description = "Id редактируемой категории", example = "1") Long id,
        @NotBlank @Schema(description = "Новое название категории", example = "Тормозная система") String name
) {
}
