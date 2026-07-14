package by.vstu.auto.parts.store.mapper;

import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.response.PartInfoResponseDto;
import by.vstu.auto.parts.store.entity.Part;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface PartMapper {

    PartInfoResponseDto mapToResponse(Part part);

    List<PartInfoResponseDto> mapToResponse(List<Part> parts);

    Part update(@MappingTarget Part part, PartEditRequestDto requestDto);

}
