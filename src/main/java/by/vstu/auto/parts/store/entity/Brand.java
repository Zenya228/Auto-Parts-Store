package by.vstu.auto.parts.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "brands")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Brand {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(columnDefinition = "BYTEA")
    private byte[] image;

    @OneToMany
    private List<Part> parts;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
