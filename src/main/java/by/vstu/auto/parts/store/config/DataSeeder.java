package by.vstu.auto.parts.store.config;

import by.vstu.auto.parts.store.entity.Brand;
import by.vstu.auto.parts.store.entity.Category;
import by.vstu.auto.parts.store.entity.Part;
import by.vstu.auto.parts.store.repository.BrandRepository;
import by.vstu.auto.parts.store.repository.CategoryRepository;
import by.vstu.auto.parts.store.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private static final List<String> CATEGORY_NAMES = List.of("Тормозная система", "Двигатель", "Подвеска", "Электрика");
    private static final List<String> BRAND_NAMES = List.of("Bosch", "Febi Bilstein", "TRW", "Sachs");

    private record SeedPart(String name, BigDecimal price, Integer stock, String categoryName, String brandName) {
    }

    private static final List<SeedPart> PARTS = List.of(
            new SeedPart("Тормозной диск передний", new BigDecimal("120.50"), 15, "Тормозная система", "Bosch"),
            new SeedPart("Тормозные колодки задние", new BigDecimal("65.00"), 30, "Тормозная система", "TRW"),
            new SeedPart("Масляный фильтр", new BigDecimal("12.90"), 50, "Двигатель", "Febi Bilstein"),
            new SeedPart("Свеча зажигания", new BigDecimal("8.40"), 100, "Двигатель", "Bosch"),
            new SeedPart("Амортизатор передний", new BigDecimal("95.00"), 20, "Подвеска", "Sachs"),
            new SeedPart("Стойка стабилизатора", new BigDecimal("28.75"), 40, "Подвеска", "TRW"),
            new SeedPart("Аккумулятор 60Ah", new BigDecimal("210.00"), 10, "Электрика", "Bosch"),
            new SeedPart("Генератор", new BigDecimal("340.00"), 5, "Электрика", "Febi Bilstein")
    );

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final PartRepository partRepository;

    @Override
    public void run(String... args) {
        int categoriesAdded = ensureCategories();
        int brandsAdded = ensureBrands();
        int partsAdded = ensureParts();

        log.info("Test data seeding done: {} categories, {} brands, {} parts added",
                categoriesAdded, brandsAdded, partsAdded);
    }

    private int ensureCategories() {
        int added = 0;
        for (String name : CATEGORY_NAMES) {
            if (!categoryRepository.existsByName(name)) {
                categoryRepository.save(Category.builder().name(name).build());
                added++;
            }
        }
        return added;
    }

    private int ensureBrands() {
        int added = 0;
        for (String name : BRAND_NAMES) {
            if (!brandRepository.existsByName(name)) {
                brandRepository.save(Brand.builder().name(name).build());
                added++;
            }
        }
        return added;
    }

    private int ensureParts() {
        Map<String, Category> categoriesByName = categoryRepository.findAll().stream()
                .collect(Collectors.toMap(Category::getName, c -> c, (a, b) -> a));
        Map<String, Brand> brandsByName = brandRepository.findAll().stream()
                .collect(Collectors.toMap(Brand::getName, b -> b, (a, b) -> a));

        int added = 0;
        for (SeedPart seedPart : PARTS) {
            if (partRepository.existsByName(seedPart.name())) {
                continue;
            }

            Category category = categoriesByName.get(seedPart.categoryName());
            Brand brand = brandsByName.get(seedPart.brandName());
            if (category == null || brand == null) {
                log.warn("Skip seeding part '{}': category or brand missing", seedPart.name());
                continue;
            }

            partRepository.save(Part.builder()
                    .name(seedPart.name())
                    .price(seedPart.price())
                    .stock(seedPart.stock())
                    .category(category)
                    .brand(brand)
                    .build());
            added++;
        }
        return added;
    }
}
