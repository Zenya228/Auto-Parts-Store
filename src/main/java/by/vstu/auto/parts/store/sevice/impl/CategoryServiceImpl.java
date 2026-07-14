package by.vstu.auto.parts.store.sevice.impl;

import by.vstu.auto.parts.store.dto.request.CategoryCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.CategoryEditRequestDto;
import by.vstu.auto.parts.store.dto.response.CategoryInfoResponseDto;
import by.vstu.auto.parts.store.entity.Category;
import by.vstu.auto.parts.store.exception.common.ResourceExceptionFactory;
import by.vstu.auto.parts.store.mapper.CategoryMapper;
import by.vstu.auto.parts.store.repository.CategoryRepository;
import by.vstu.auto.parts.store.sevice.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> ResourceExceptionFactory.CategoryNotFoundException(id)
        );
    }

    @Override
    public Page<CategoryInfoResponseDto> getCategoriesByPage(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);

        return categories.map(categoryMapper::mapToResponse);
    }

    @Override
    public CategoryInfoResponseDto getById(Long id) {
        return categoryMapper.mapToResponse(findCategoryById(id));
    }

    @Override
    public CategoryInfoResponseDto create(CategoryCreateRequestDto requestDto) {

        Category category = Category.builder()
                .name(requestDto.name())
                .build();

        Category categoryFromDb = categoryRepository.save(category);

        return categoryMapper.mapToResponse(categoryFromDb);
    }

    @Override
    public CategoryInfoResponseDto edit(CategoryEditRequestDto requestDto) {

        Category categoryFromDb = findCategoryById(requestDto.id());

        categoryMapper.update(categoryFromDb, requestDto);

        categoryFromDb = categoryRepository.save(categoryFromDb);

        return categoryMapper.mapToResponse(categoryFromDb);
    }

    @Override
    public CategoryInfoResponseDto delete(Long id) {

        Category categoryFromDb = findCategoryById(id);

        CategoryInfoResponseDto categoryInfoResponseDto = categoryMapper.mapToResponse(categoryFromDb);

        categoryRepository.delete(categoryFromDb);

        return categoryInfoResponseDto;
    }
}
