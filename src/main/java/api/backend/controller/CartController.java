package api.backend.controller;

import api.backend.dto.cart.AddToCartDTO;
import api.backend.dto.cart.CartDTO;
import api.backend.entities.User;
import api.backend.repository.UserReposritory;
import api.backend.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;
    private final UserReposritory userReposritory;
    public CartController(CartService cartService, UserReposritory userReposritory) {
        this.cartService = cartService;
        this.userReposritory = userReposritory;
    }

    //post to add to cart
    @PostMapping("/cart/addItem")
    public ResponseEntity<HttpStatus> addItemToCart(@RequestBody AddToCartDTO addToCartDTO, @RequestParam("userID") Long userID){
        cartService.addToCart(addToCartDTO,userID);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    //get to get all products from cart
    @GetMapping("/cart/getItem")
    public ResponseEntity<CartDTO> getCartItems(@RequestParam("userID") Long userID){
        return ResponseEntity.ok(cartService.listOfCartItems(userID));
    }
    //delete cart item for user
    @DeleteMapping("/cart/removeItem/{cartItemID}")
    public ResponseEntity<String> deleteItem(@PathVariable("cartItemID") Long cartItemID,
                                             @RequestParam("userID") Long userID){
        Optional<User> currentUser=userReposritory.findById(userID);
        cartService.removeItem(cartItemID,currentUser.get());
        return ResponseEntity.ok("Successfully removed item!");
    }
}
