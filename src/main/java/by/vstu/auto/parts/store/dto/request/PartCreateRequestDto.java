package by.vstu.auto.parts.store.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record PartCreateRequestDto(
        String name,
        MultipartFile image,
        Long categoryId,
        Long brandId
) {
}
