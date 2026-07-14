package by.vstu.auto.parts.store.repository;

import by.vstu.auto.parts.store.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
}
