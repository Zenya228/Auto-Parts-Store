package by.vstu.auto.parts.store.mapper;

import by.vstu.auto.parts.store.dto.request.CategoryEditRequestDto;
import by.vstu.auto.parts.store.dto.response.CategoryInfoResponseDto;
import by.vstu.auto.parts.store.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface CategoryMapper {

    CategoryInfoResponseDto mapToResponse(Category category);

    List<CategoryInfoResponseDto> mapToResponse(List<Category> category);

    Category update(@MappingTarget Category category, CategoryEditRequestDto requestDto);

}
