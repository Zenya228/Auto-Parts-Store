package by.vstu.auto.parts.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Информация о бренде")
public record BrandInfoResponseDto (
        @Schema(description = "Id бренда", example = "1") Long id,
        @Schema(description = "Название бренда", example = "Bosch") String name,
        @Schema(description = "Дата и время создания бренда") LocalDateTime createdAt
){
}
