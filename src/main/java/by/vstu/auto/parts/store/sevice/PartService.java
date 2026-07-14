package by.vstu.auto.parts.store.sevice;

import by.vstu.auto.parts.store.dto.request.PartCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.response.PartInfoResponseDto;
import by.vstu.auto.parts.store.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartService {

    Page<Part> getPartsByPage(Pageable pageable);

    PartInfoResponseDto create(PartCreateRequestDto requestDto);

    PartInfoResponseDto edit(PartEditRequestDto requestDto);

    PartInfoResponseDto delete(Long id);

}
