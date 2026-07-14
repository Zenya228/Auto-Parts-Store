package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Данные для редактирования запчасти")
public record PartEditRequestDto(
        @NotNull @Schema(description = "Id редактируемой запчасти", example = "1") Long id,
        @NotBlank @Schema(description = "Новое название запчасти", example = "Тормозной диск") String name,
        @NotNull @DecimalMin(value = "0", inclusive = true) @Schema(description = "Новая цена запчасти", example = "366.3") BigDecimal price,
        @NotNull @Min(0) @Schema(description = "Новое кол-во в наличии запчастей", example = "5") Integer stock
) {
}
