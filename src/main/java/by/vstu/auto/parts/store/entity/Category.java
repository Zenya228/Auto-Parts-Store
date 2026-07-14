package by.vstu.auto.parts.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Part> parts;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
