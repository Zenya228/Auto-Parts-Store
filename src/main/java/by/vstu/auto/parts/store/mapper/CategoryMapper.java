package by.vstu.auto.parts.store.mapper;

import by.vstu.auto.parts.store.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface CategoryMapper {

    Category mapToResponse(Category category);

    List<Category> mapToResponse(List<Category> category);

}
