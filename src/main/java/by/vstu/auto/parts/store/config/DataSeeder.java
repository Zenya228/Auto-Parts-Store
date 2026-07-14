package by.vstu.auto.parts.store.config;

import by.vstu.auto.parts.store.entity.Brand;
import by.vstu.auto.parts.store.entity.Category;
import by.vstu.auto.parts.store.entity.Part;
import by.vstu.auto.parts.store.repository.BrandRepository;
import by.vstu.auto.parts.store.repository.CategoryRepository;
import by.vstu.auto.parts.store.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final PartRepository partRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            return;
        }

        List<Category> categories = categoryRepository.saveAll(List.of(
                Category.builder().name("Тормозная система").build(),
                Category.builder().name("Двигатель").build(),
                Category.builder().name("Подвеска").build(),
                Category.builder().name("Электрика").build()
        ));

        List<Brand> brands = brandRepository.saveAll(List.of(
                Brand.builder().name("Bosch").build(),
                Brand.builder().name("Febi Bilstein").build(),
                Brand.builder().name("TRW").build(),
                Brand.builder().name("Sachs").build()
        ));

        partRepository.saveAll(List.of(
                Part.builder().name("Тормозной диск передний").price(new BigDecimal("120.50")).stock(15)
                        .category(categories.get(0)).brand(brands.get(0)).build(),
                Part.builder().name("Тормозные колодки задние").price(new BigDecimal("65.00")).stock(30)
                        .category(categories.get(0)).brand(brands.get(2)).build(),
                Part.builder().name("Масляный фильтр").price(new BigDecimal("12.90")).stock(50)
                        .category(categories.get(1)).brand(brands.get(1)).build(),
                Part.builder().name("Свеча зажигания").price(new BigDecimal("8.40")).stock(100)
                        .category(categories.get(1)).brand(brands.get(0)).build(),
                Part.builder().name("Амортизатор передний").price(new BigDecimal("95.00")).stock(20)
                        .category(categories.get(2)).brand(brands.get(3)).build(),
                Part.builder().name("Стойка стабилизатора").price(new BigDecimal("28.75")).stock(40)
                        .category(categories.get(2)).brand(brands.get(2)).build(),
                Part.builder().name("Аккумулятор 60Ah").price(new BigDecimal("210.00")).stock(10)
                        .category(categories.get(3)).brand(brands.get(0)).build(),
                Part.builder().name("Генератор").price(new BigDecimal("340.00")).stock(5)
                        .category(categories.get(3)).brand(brands.get(1)).build()
        ));
    }
}
