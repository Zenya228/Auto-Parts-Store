package by.vstu.auto.parts.store.sevice;

import by.vstu.auto.parts.store.dto.request.BrandCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.response.BrandInfoResponseDto;
import by.vstu.auto.parts.store.dto.response.PartInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartService {

    // PARTS

    Page<PartInfoResponseDto> getPartsByPage(Pageable pageable);

    PartInfoResponseDto getById(Long id);

    PartInfoResponseDto create(PartCreateRequestDto requestDto);

    PartInfoResponseDto edit(PartEditRequestDto requestDto);

    PartInfoResponseDto delete(Long id);

    byte[] getImage(Long id);

    // BRANDS

    Page<BrandInfoResponseDto> getBrandsByPage(Pageable pageable);

    BrandInfoResponseDto createBrand(BrandCreateRequestDto requestDto);

    BrandInfoResponseDto deleteBrand(Long id);

}
