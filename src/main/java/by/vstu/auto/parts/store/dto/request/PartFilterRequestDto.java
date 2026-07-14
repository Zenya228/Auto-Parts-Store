package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Фильтр для поиска запчастей")
public record PartFilterRequestDto(
        @Schema(description = "Id категории") Long categoryId,
        @Schema(description = "Id бренда") Long brandId,
        @Schema(description = "Минимальная цена") BigDecimal minPrice,
        @Schema(description = "Максимальная цена") BigDecimal maxPrice,
        @Schema(description = "Показывать только позиции в наличии") Boolean inStockOnly
) {
    public static PartFilterRequestDto empty() {
        return new PartFilterRequestDto(null, null, null, null, null);
    }
}
