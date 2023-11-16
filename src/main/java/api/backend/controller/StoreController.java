package api.backend.controller;

import api.backend.dto.ProductDTO;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.services.CategoryService;
import api.backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final ProductService productService;
    private final CategoryService categoryService;
    public StoreController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService=categoryService;
    }

    //All users of the application should be able to search for products
    @GetMapping("/products/searchAll")
    public List<ProductDTO> getAllProducts(){
        return productService.allProducts();
    }
    @GetMapping("/products/searchBy/{category}")
    public List<ProductDTO> getByCategory(@PathVariable("category") String categoryName){
        return productService.productsByCategory(categoryName);
    }
    //All users should be able to search for categories
    //get a category
    @GetMapping("/category/searchAll")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/category/search/{categoryName}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable("categoryName") String categoryName){
        return ResponseEntity.ok(categoryService.getACategory(categoryName));
    }
}
