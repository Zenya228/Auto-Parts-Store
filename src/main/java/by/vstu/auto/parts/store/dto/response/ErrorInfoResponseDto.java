package by.vstu.auto.parts.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация об ошибке")
public record ErrorInfoResponseDto(
        @Schema(description = "HTTP статус ошибки", example = "404") int status,
        @Schema(description = "Сообщение об ошибке", example = "Category with id 1 not found") String message
) {
}
