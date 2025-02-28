package ProductBackendTest.service.Impl;

import ProductBackendTest.dto.ProductDto;

import java.util.List;

public interface ProductServiceImpl {
     List<ProductDto> getAllProducts();
     ProductDto getProductById(Long id);
     ProductDto saveProduct(ProductDto productDto);
     ProductDto updateProduct(Long id, ProductDto productDto);
     void deleteProduct(Long id);

}
