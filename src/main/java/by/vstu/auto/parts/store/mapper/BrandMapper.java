package by.vstu.auto.parts.store.mapper;

import by.vstu.auto.parts.store.dto.response.BrandInfoResponseDto;
import by.vstu.auto.parts.store.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface BrandMapper {

    BrandInfoResponseDto mapToResponse(Brand brand);

    List<BrandInfoResponseDto> mapToResponse(List<Brand> brands);

}
