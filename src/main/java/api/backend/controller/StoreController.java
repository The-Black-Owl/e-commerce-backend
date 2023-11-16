package api.backend.controller;

import api.backend.dto.ProductDTO;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.services.CategoryService;
import api.backend.services.ProductService;
import api.backend.services.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final WishlistService wishlistService;
    public StoreController(ProductService productService, CategoryService categoryService, WishlistService wishlistService) {
        this.productService = productService;
        this.categoryService=categoryService;
        this.wishlistService = wishlistService;
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
    @GetMapping("/category/searchAll")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/category/search/{categoryName}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable("categoryName") String categoryName){
        return ResponseEntity.ok(categoryService.getACategory(categoryName));
    }
    //All store users should be able to make a wishlist
    @PostMapping("/wishlist/{userID}")
    public HttpStatus createWishList(@PathVariable("userID") Long userID, @RequestBody Products products){
        wishlistService.saveWishList(userID,products);
        return HttpStatus.CREATED;
    }
    @DeleteMapping("/wishlist/{id}")
    public HttpStatus deleteWishlist(@PathVariable("id")Long id){
        wishlistService.removeWishlist(id);
        return HttpStatus.OK;
    }
    //get wishlist
}
