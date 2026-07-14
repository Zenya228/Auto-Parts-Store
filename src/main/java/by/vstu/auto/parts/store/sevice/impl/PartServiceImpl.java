package by.vstu.auto.parts.store.sevice.impl;

import by.vstu.auto.parts.store.dto.request.PartCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.response.PartInfoResponseDto;
import by.vstu.auto.parts.store.entity.Part;
import by.vstu.auto.parts.store.sevice.PartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PartServiceImpl implements PartService {
    @Override
    public Page<Part> getPartsByPage(Pageable pageable) {
        return null;
    }

    @Override
    public PartInfoResponseDto create(PartCreateRequestDto requestDto) {
        return null;
    }

    @Override
    public PartInfoResponseDto edit(PartEditRequestDto requestDto) {
        return null;
    }

    @Override
    public PartInfoResponseDto delete(Long id) {
        return null;
    }
}
