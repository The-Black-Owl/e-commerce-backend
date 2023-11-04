package api.backend.controller;

import api.backend.dto.ProductDTO;
import api.backend.dto.ProductRequest;
import api.backend.entities.Products;
import api.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    //delete users
    //count number of users on the system
    //see items being shipped
    //check stock count

    //method to get all items available on the DB
    @GetMapping("/all_items")
    public List<ProductDTO> getAllProducts(){
        return productService.allProducts();
    }

    //method to get items by category
    @GetMapping("/products/{category}")
    public List<ProductDTO> getByCategory(@PathVariable("category") String categoryName){
        return productService.productsByCategory(categoryName);
    }

    //create items
    @PostMapping("/add_product")
    public ResponseEntity<ProductDTO> createProducts(@RequestBody ProductRequest productRequest){
        ProductDTO product=productService.newProduct(productRequest);
        return ResponseEntity.ok(product);
    }
    //remove items
    @DeleteMapping("/remove_product/{SKU}")
    public ResponseEntity<String> removeProduct(@PathVariable("SKU") Long sku){
        productService.deleteProduct(sku);
        return ResponseEntity.ok("Successfully removed item!");
    }
    //update items
    @PutMapping("/upadte_product/{SKU}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductRequest request, @PathVariable("SKU") Long sku){
        ProductDTO updatedProduct=productService.updateProduct(sku,request);
        return ResponseEntity.ok(updatedProduct);
    }

}
