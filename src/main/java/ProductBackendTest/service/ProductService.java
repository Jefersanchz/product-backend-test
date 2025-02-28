package ProductBackendTest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProductBackendTest.dto.ProductDto;
import ProductBackendTest.entity.ProductEntity;
import ProductBackendTest.repository.ProductRepository;
import ProductBackendTest.service.Impl.ProductServiceImpl;

import java.util.List;

import static ProductBackendTest.mapper.ProductMapper.*;

@Service
public class ProductService implements ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntitiesToProductDtos(productEntities);
    }

    public ProductDto getProductById(Long id) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        assert product != null;
        return productEntityToProductDto(product);
    }

    public ProductDto saveProduct(ProductDto productDto) {
        ProductEntity product = productDtoToProductEntity(productDto);
        ProductEntity savedProductEntity = productRepository.save(product);
        return productEntityToProductDto(savedProductEntity);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductEntity existingProductEntity = productRepository.findById(id).orElse(null);

        if (existingProductEntity != null) {
            existingProductEntity.setName(productDto.getName());
            existingProductEntity.setDescription(productDto.getDescription());
            existingProductEntity.setPrice(productDto.getPrice());
            existingProductEntity.setQuantity(productDto.getQuantity());
            ProductEntity updatedProductEntity = productRepository.save(existingProductEntity);
            return productEntityToProductDto(updatedProductEntity);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
