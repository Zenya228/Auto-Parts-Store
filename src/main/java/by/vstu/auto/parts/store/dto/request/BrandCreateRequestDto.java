package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания бренда")
public record BrandCreateRequestDto(
        @Schema(description = "Название бренда", example = "Bosch") String name
) {
}
