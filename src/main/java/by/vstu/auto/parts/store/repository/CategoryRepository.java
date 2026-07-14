package by.vstu.auto.parts.store.repository;

import by.vstu.auto.parts.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
