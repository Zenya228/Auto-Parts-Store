package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "Данные для создания запчасти")
public record PartCreateRequestDto(
        @Schema(description = "Название запчасти", example = "Тормозной диск") String name,
        @Schema(description = "Изображение запчасти") MultipartFile image,
        @Schema(description = "Id категории, к которой относится запчасть", example = "1") Long categoryId,
        @Schema(description = "Id бренда запчасти", example = "1") Long brandId
) {
}
