package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Данные для создания бренда")
public record BrandCreateRequestDto(
        @NotBlank @Schema(description = "Название бренда", example = "Bosch") String name
) {
}
