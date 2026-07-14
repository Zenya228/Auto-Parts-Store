package by.vstu.auto.parts.store.sevice.impl;

import by.vstu.auto.parts.store.dto.request.BrandCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartCreateRequestDto;
import by.vstu.auto.parts.store.dto.request.PartEditRequestDto;
import by.vstu.auto.parts.store.dto.request.PartFilterRequestDto;
import by.vstu.auto.parts.store.dto.response.BrandInfoResponseDto;
import by.vstu.auto.parts.store.dto.response.CategoryInfoResponseDto;
import by.vstu.auto.parts.store.dto.response.PartInfoResponseDto;
import by.vstu.auto.parts.store.entity.Brand;
import by.vstu.auto.parts.store.entity.Category;
import by.vstu.auto.parts.store.entity.Part;
import by.vstu.auto.parts.store.exception.common.ResourceExceptionFactory;
import by.vstu.auto.parts.store.mapper.BrandMapper;
import by.vstu.auto.parts.store.mapper.PartMapper;
import by.vstu.auto.parts.store.repository.BrandRepository;
import by.vstu.auto.parts.store.repository.CategoryRepository;
import by.vstu.auto.parts.store.repository.PartRepository;
import by.vstu.auto.parts.store.repository.specification.PartSpecifications;
import by.vstu.auto.parts.store.sevice.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final PartMapper partMapper;
    private final BrandMapper brandMapper;

    private Part findPartById(Long id) {
        return partRepository.findById(id).orElseThrow(
                () -> ResourceExceptionFactory.PartNotFoundException(id)
        );
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> ResourceExceptionFactory.CategoryNotFoundException(id)
        );
    }

    private Brand findBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow(
                () -> ResourceExceptionFactory.BrandNotFoundException(id)
        );
    }

    // PARTS

    @Override
    public Page<PartInfoResponseDto> getPartsByPage(PartFilterRequestDto filter, Pageable pageable) {
        Page<Part> parts = partRepository.findAll(PartSpecifications.filter(filter), pageable);

        return parts.map(partMapper::mapToResponse);
    }

    @Override
    public PartInfoResponseDto getById(Long id) {
        return partMapper.mapToResponse(findPartById(id));
    }

    @Override
    public PartInfoResponseDto create(PartCreateRequestDto requestDto) {

        Part part = Part.builder()
                .name(requestDto.name())
                .image(readImage(requestDto.image()))
                .price(requestDto.price())
                .stock(requestDto.stock())
                .category(findCategoryById(requestDto.categoryId()))
                .brand(findBrandById(requestDto.brandId()))
                .build();

        Part partFromDb = partRepository.save(part);

        return partMapper.mapToResponse(partFromDb);
    }

    private byte[] readImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return null;
        }

        try {
            return image.getBytes();
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read part image", e);
        }
    }

    @Override
    public PartInfoResponseDto edit(PartEditRequestDto requestDto) {

        Part partFromDb = findPartById(requestDto.id());

        partMapper.update(partFromDb, requestDto);

        partFromDb = partRepository.save(partFromDb);

        return partMapper.mapToResponse(partFromDb);
    }

    @Override
    public PartInfoResponseDto delete(Long id) {
        Part partFromDb = findPartById(id);

        PartInfoResponseDto partInfoResponseDto = partMapper.mapToResponse(partFromDb);

        partRepository.delete(partFromDb);

        return partInfoResponseDto;
    }

    @Override
    public byte[] getImage(Long id) {
        return findPartById(id).getImage();
    }


    // BRANDS


    @Override
    public Page<BrandInfoResponseDto> getBrandsByPage(Pageable pageable) {
        Page<Brand> parts = brandRepository.findAll(pageable);

        return parts.map(brandMapper::mapToResponse);
    }

    @Override
    public BrandInfoResponseDto createBrand(BrandCreateRequestDto requestDto) {

        Brand brand = Brand.builder()
                .name(requestDto.name())
                .build();

        Brand brandFromDb = brandRepository.save(brand);

        return brandMapper.mapToResponse(brandFromDb);
    }

    @Override
    public BrandInfoResponseDto deleteBrand(Long id) {

        Brand brandFromDb = findBrandById(id);

        BrandInfoResponseDto brandInfoResponseDto = brandMapper.mapToResponse(brandFromDb);

        brandRepository.delete(brandFromDb);

        return brandInfoResponseDto;
    }
}
