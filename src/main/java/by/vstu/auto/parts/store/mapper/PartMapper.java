package by.vstu.auto.parts.store.mapper;

import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.response.PartInfoResponseDto;
import by.vstu.auto.parts.store.entity.Brand;
import by.vstu.auto.parts.store.entity.Category;
import by.vstu.auto.parts.store.entity.Part;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface PartMapper {

    PartInfoResponseDto mapToResponse(Part part);

    @Mapping(source = "category", target = "categoryId", qualifiedByName = "getCategoryId")
    @Mapping(source = "brand", target = "brandId", qualifiedByName = "getBrandId")
    List<PartInfoResponseDto> mapToResponse(List<Part> parts);

    Part update(@MappingTarget Part part, PartEditRequestDto requestDto);

    @Named("getCategoryId")
    default Long getCategoryId(Category category) {
        return category.getId();
    }

    @Named("getBrandId")
    default Long getBrandId(Brand brand) {
        return brand.getId();
    }

}
