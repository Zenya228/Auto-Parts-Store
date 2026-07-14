package by.vstu.auto.parts.store.sevice;

import by.vstu.auto.parts.store.dto.request.CategoryCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.CategoryEditRequestDto;
import by.vstu.auto.parts.store.dto.response.CategoryInfoResponseDto;
import by.vstu.auto.parts.store.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<Category> getCategoriesByPage(Pageable pageable);

    CategoryInfoResponseDto create(CategoryCreateRequestDto requestDto);

    CategoryInfoResponseDto edit(CategoryEditRequestDto requestDto);

    CategoryInfoResponseDto delete(Long id);

}
