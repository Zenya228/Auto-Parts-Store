package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Schema(description = "Данные для создания запчасти")
public record PartCreateRequestDto(
        @NotBlank @Schema(description = "Название запчасти", example = "Тормозной диск") String name,
        @Schema(description = "Изображение запчасти") MultipartFile image,
        @NotNull @DecimalMin(value = "0", inclusive = true) @Schema(description = "Цена запчасти", example = "366.3") BigDecimal price,
        @NotNull @Min(0) @Schema(description = "Кол-во в наличии запчастей", example = "5") Integer stock,
        @NotNull @Schema(description = "Id категории, к которой относится запчасть", example = "1") Long categoryId,
        @NotNull @Schema(description = "Id бренда запчасти", example = "1") Long brandId
) {
}
