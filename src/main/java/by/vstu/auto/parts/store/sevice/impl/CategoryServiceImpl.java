package by.vstu.auto.parts.store.sevice.impl;

import by.vstu.auto.parts.store.dto.request.CategoryCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.CategoryEditRequestDto;
import by.vstu.auto.parts.store.dto.response.CategoryInfoResponseDto;
import by.vstu.auto.parts.store.entity.Category;
import by.vstu.auto.parts.store.sevice.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Page<Category> getCategoriesByPage(Pageable pageable) {
        return null;
    }

    @Override
    public CategoryInfoResponseDto create(CategoryCreateRequestDto requestDto) {
        return null;
    }

    @Override
    public CategoryInfoResponseDto edit(CategoryEditRequestDto requestDto) {
        return null;
    }

    @Override
    public CategoryInfoResponseDto delete(Long id) {
        return null;
    }
}
