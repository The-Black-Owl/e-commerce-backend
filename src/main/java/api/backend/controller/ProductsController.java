package api.backend.controller;

import api.backend.dto.product.ProductDTO;
import api.backend.dto.requestRecords.ProductRequest;
import api.backend.entities.Products;
import api.backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products/create")
    public ResponseEntity<Products> createProducts(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.newProduct(productRequest));
    }
    @GetMapping("/products/searchAll")
    public List<ProductDTO> getAllProducts(){
        return productService.allProducts();
    }
    @GetMapping("/products/searchBy/{category}")
    public List<ProductDTO> getByCategory(@PathVariable("category") String categoryName){
        return productService.productsByCategory(categoryName);
    }
    @DeleteMapping("/products/delete/{SKU}")
    public ResponseEntity<String> removeProduct(@PathVariable("SKU") Long sku){
        productService.deleteProduct(sku);
        return ResponseEntity.ok("Successfully removed item!");
    }
    @PutMapping("/products/update/{SKU}")
    public ResponseEntity<Optional<ProductDTO>> updateProduct(@RequestBody ProductRequest request, @PathVariable("SKU") Long sku){
        Optional<ProductDTO> updatedProduct=productService.updateProduct(sku,request);
        return ResponseEntity.ok(updatedProduct);
    }
}
