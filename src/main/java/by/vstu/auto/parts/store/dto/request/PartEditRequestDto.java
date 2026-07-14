package by.vstu.auto.parts.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для редактирования запчасти")
public record PartEditRequestDto(
        @Schema(description = "Id редактируемой запчасти", example = "1") Long id,
        @Schema(description = "Новое название запчасти", example = "Тормозной диск") String name
) {
}
