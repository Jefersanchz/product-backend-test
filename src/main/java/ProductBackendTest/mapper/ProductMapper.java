package ProductBackendTest.mapper;



import java.util.List;
import java.util.stream.Collectors;

import ProductBackendTest.dto.ProductDto;
import ProductBackendTest.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity productDtoToProductEntity(ProductDto productDto) {
        return ProductEntity
                .builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }

    public static ProductDto productEntityToProductDto(ProductEntity productEntity) {
        return ProductDto
                .builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .build();
    }

    public static List<ProductDto> productEntitiesToProductDtos(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductMapper::productEntityToProductDto)
                .collect(Collectors.toList());
    }

}
