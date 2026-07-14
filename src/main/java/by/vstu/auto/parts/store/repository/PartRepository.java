package by.vstu.auto.parts.store.repository;

import by.vstu.auto.parts.store.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
    boolean existsByName(String name);
}
