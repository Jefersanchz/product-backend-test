package ProductBackendTest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor 
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    private ProductEntity product;

    private Integer quantity;
    private Double total;

    @Builder
    public OrderEntity(LocalDateTime date, ProductEntity product, Integer quantity, Double total) {
        this.date = date;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }
}
