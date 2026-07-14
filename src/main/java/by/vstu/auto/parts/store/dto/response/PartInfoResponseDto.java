package by.vstu.auto.parts.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Информация о запчасти")
public record PartInfoResponseDto(
        @Schema(description = "Id запчасти", example = "1") Long id,
        @Schema(description = "Название запчасти", example = "Тормозной диск") String name,
        @Schema(description = "Цена запчасти", example = "366.3") BigDecimal price,
        @Schema(description = "Кол-во в наличии запчастей", example = "5") Integer stock,
        @Schema(description = "Id бренда запчасти", example = "1") Long brandId,
        @Schema(description = "Id категории запчасти", example = "1") Long categoryId,
        @Schema(description = "Дата и время создания запчасти") LocalDateTime createdAt
) {
}
