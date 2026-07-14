package by.vstu.auto.parts.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Информация о категории")
public record CategoryInfoResponseDto(
        @Schema(description = "Id категории", example = "1") Long id,
        @Schema(description = "Название категории", example = "Тормозная система") String name,
        @Schema(description = "Дата и время создания категории") LocalDateTime createdAt
) {
}
