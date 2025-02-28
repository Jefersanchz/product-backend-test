package ProductBackendTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import ProductBackendTest.dto.ProductDto;
import ProductBackendTest.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductDto save(ProductDto product);
}
