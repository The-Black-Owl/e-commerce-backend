package api.backend.controller;

import api.backend.dto.product.ProductDTO;
import api.backend.entities.Products;
import api.backend.services.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WishListController {
    private final WishlistService wishlistService;

    public WishListController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }
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
    @GetMapping("/wishlist/find/{userID}")
    public ResponseEntity<List<ProductDTO>> getUserWishlist(@PathVariable("userID")Long id){
        return ResponseEntity.ok(wishlistService.getWishlistForUser(id));
    }
}
