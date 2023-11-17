package api.backend.services;

import api.backend.dto.cart.AddToCartDTO;
import api.backend.dto.cart.CartDTO;
import api.backend.dto.cart.CartItemsDTO;
import api.backend.entities.Cart;
import api.backend.entities.Products;
import api.backend.entities.User;
import api.backend.exceptions.ResourceNotFoundException;
import api.backend.repository.CartRepository;
import api.backend.repository.ProductRepository;
import api.backend.repository.UserReposritory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserReposritory userReposritory;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, UserReposritory userReposritory, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userReposritory = userReposritory;
        this.productRepository = productRepository;
    }


    //add item to cart
    public void addToCart(AddToCartDTO addToCartDTO,Long userID){
        Optional<User> currentUser=userReposritory.findById(userID);
        Optional<Products> optionalProducts=productRepository.findById(addToCartDTO.getProductID());
        if (optionalProducts.isEmpty())throw new ResourceNotFoundException("Product not found: "+addToCartDTO.getProductID());
        Cart cart=new Cart(addToCartDTO.getQuantity(),optionalProducts.get(),currentUser.get());
        cartRepository.save(cart);
    }

    public CartDTO listOfCartItems(Long userID) {
        Optional<User> currentUser=userReposritory.findById(userID);
        List<Cart> cartList=cartRepository.findAllByUserOrOrderByCreatedAtDesc(currentUser.get());
        List<CartItemsDTO> cartItemsDTOList=new ArrayList<>();
        double totalCost=0;
        for(Cart item: cartList){
            CartItemsDTO cartItemsDTO=new CartItemsDTO(item);
            totalCost+=cartItemsDTO.getQuantity()*cartItemsDTO.getProducts().getPrice();
            cartItemsDTOList.add(cartItemsDTO);
        }
        return new CartDTO(cartItemsDTOList,totalCost);
    }

    public void removeItem(Long cartItemID, User user) {
        Optional<Cart> optionalCart=cartRepository.findById(cartItemID);
        if (optionalCart.isEmpty())throw new ResourceNotFoundException("Cart not found:"+cartItemID);
        Cart cart=optionalCart.get();
        if(cart.getUser()!=user)throw new ResourceNotFoundException("Cart doesnt belong to user");
        cartRepository.delete(cart);
    }
}
