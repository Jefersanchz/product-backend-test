package ProductBackendTest.controller;

import ProductBackendTest.dto.ProductDto;
import ProductBackendTest.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ProductBackendTest.constan.PathGeneric.PATH_API_PRODUCT;
import static ProductBackendTest.constan.PathGeneric.PATH_ID;

@RestController
@RequestMapping(value = PATH_API_PRODUCT)
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productResponse = productServiceImpl.getAllProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping(value = PATH_ID)
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productResponse = productServiceImpl.getProductById(id);
        if (productResponse != null) {
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto productResponse = productServiceImpl.saveProduct(productDto);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = PATH_ID)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        ProductDto productResponse = productServiceImpl.updateProduct(id, productDto);

        if (productResponse != null) {
            return ResponseEntity.ok(productResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = PATH_ID)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productServiceImpl.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
