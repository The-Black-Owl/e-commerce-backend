package api.backend.controller;

import api.backend.dto.ProductDTO;
import api.backend.dto.requestRecords.CategoryRequest;
import api.backend.dto.requestRecords.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.services.CategoryService;
import api.backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    //create items
    @PostMapping("/products/create")
    public ResponseEntity<Products> createProducts(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.newProduct(productRequest));
    }
    //remove items
    @DeleteMapping("/product/delete/{SKU}")
    public ResponseEntity<String> removeProduct(@PathVariable("SKU") Long sku){
        productService.deleteProduct(sku);
        return ResponseEntity.ok("Successfully removed item!");
    }
    //update items
    @PutMapping("/product/update/{SKU}")
    public ResponseEntity<Optional<ProductDTO>> updateProduct(@RequestBody ProductRequest request, @PathVariable("SKU") Long sku){
        Optional<ProductDTO> updatedProduct=productService.updateProduct(sku,request);
        return ResponseEntity.ok(updatedProduct);
    }

    //create a category
    @PostMapping("/category/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest){
        Category category=categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(category);
    }

    //update a category
    @PutMapping("/category/update/{categoryID}")
    public ResponseEntity<Optional<Category>> updateCategory(@PathVariable("categoryID") Long id, @RequestBody CategoryRequest request){
        Optional<Category> updateCategory=categoryService.updateCategory(id,request);
        return ResponseEntity.ok(updateCategory);
    }
    //delete a category
    @DeleteMapping("/category/delete/{categoryID}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryID") Long id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

}
