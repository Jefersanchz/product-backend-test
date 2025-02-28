package ProductBackendTest.mapper;



import java.util.List;
import java.util.stream.Collectors;

import ProductBackendTest.dto.OrderDto;
import ProductBackendTest.dto.ProductDto;
import ProductBackendTest.entity.OrderEntity;
import ProductBackendTest.entity.ProductEntity;

import static ProductBackendTest.mapper.ProductMapper.productDtoToProductEntity;
import static ProductBackendTest.mapper.ProductMapper.productEntityToProductDto;

public class OrderMapper {

    public static OrderEntity orderDtoToOrderEntity(OrderDto orderDto) {

        ProductEntity productEntity = productDtoToProductEntity(orderDto.getProduct());

        return OrderEntity
                .builder()
                .date(orderDto.getDate())
                .quantity(orderDto.getQuantity())
                .total(orderDto.getTotal())
                .product(productEntity)
                .build();
    }

    public static OrderDto orderEntityToOrderDto(OrderEntity orderEntity) {
        ProductDto productDto = productEntityToProductDto(orderEntity.getProduct());

        return OrderDto.builder()
                .id(orderEntity.getId())
                .date(orderEntity.getDate())
                .quantity(orderEntity.getQuantity())
                .total(orderEntity.getTotal())
                .product(productDto)
                .build();
    }

    public static List<OrderDto> orderEntitiesToOrderDtos(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(OrderMapper::orderEntityToOrderDto)
                .collect(Collectors.toList());
    }
}
