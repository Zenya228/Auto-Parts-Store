package by.vstu.auto.parts.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "parts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Part {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(columnDefinition = "BYTEA")
    private byte[] image;

    private BigDecimal price;

    private Integer stock;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
