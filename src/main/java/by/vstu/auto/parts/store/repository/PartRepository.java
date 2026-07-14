package by.vstu.auto.parts.store.repository;

import by.vstu.auto.parts.store.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartRepository extends JpaRepository<Part, Long>, JpaSpecificationExecutor<Part> {
    boolean existsByName(String name);
}
