package ProductBackendTest.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OrderDto {
    private Long id;
    private LocalDateTime date;
    private ProductDto product;
    private Integer quantity;
    private Double total;
}
