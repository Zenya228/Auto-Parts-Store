package by.vstu.auto.parts.store.repository.specification;

import by.vstu.auto.parts.store.dto.request.PartFilterRequestDto;
import by.vstu.auto.parts.store.entity.Part;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class PartSpecifications {

    private PartSpecifications() {
    }

    public static Specification<Part> filter(PartFilterRequestDto filter) {
        return Specification
                .<Part>where(categoryIdEquals(filter.categoryId()))
                .and(brandIdEquals(filter.brandId()))
                .and(priceGreaterOrEqual(filter.minPrice()))
                .and(priceLessOrEqual(filter.maxPrice()))
                .and(inStock(filter.inStockOnly()));
    }

    private static Specification<Part> categoryIdEquals(Long categoryId) {
        return (root, query, cb) -> categoryId == null ? null : cb.equal(root.get("category").get("id"), categoryId);
    }

    private static Specification<Part> brandIdEquals(Long brandId) {
        return (root, query, cb) -> brandId == null ? null : cb.equal(root.get("brand").get("id"), brandId);
    }

    private static Specification<Part> priceGreaterOrEqual(BigDecimal minPrice) {
        return (root, query, cb) -> minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Part> priceLessOrEqual(BigDecimal maxPrice) {
        return (root, query, cb) -> maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<Part> inStock(Boolean inStockOnly) {
        return (root, query, cb) -> (inStockOnly == null || !inStockOnly) ? null : cb.greaterThan(root.get("stock"), 0);
    }
}
