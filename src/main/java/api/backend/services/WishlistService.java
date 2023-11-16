package api.backend.services;

import api.backend.entities.Products;
import api.backend.entities.User;
import api.backend.entities.Wishlist;
import api.backend.mapper.ProductsDTOmapper;
import api.backend.repository.ProductRepository;
import api.backend.repository.UserReposritory;
import api.backend.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {
    private final ProductsDTOmapper productMapper;
    private final UserReposritory userReposritory;
    private final ProductRepository productRepository;
    private final WishlistRepository wishlistRepository;
    public WishlistService(ProductsDTOmapper productMapper, UserReposritory userReposritory, ProductRepository productRepository, WishlistRepository wishlistRepository) {
        this.productMapper = productMapper;
        this.userReposritory = userReposritory;
        this.productRepository = productRepository;
        this.wishlistRepository = wishlistRepository;
    }

    //save products as wishlist
    public void saveWishList(Long userID, Products products){
        User getUser=userReposritory.findById(userID).get();
        Products product= (Products) productRepository.findById(products.getProductID()).stream().map(productMapper);
        Wishlist wishlist=new Wishlist(getUser,product);
        wishlistRepository.save(wishlist);
    }
    //get wishlist
    public Optional<Wishlist> getWishlist(Long wishListID){
        return wishlistRepository.findById(wishListID);
    }
    public void removeWishlist(Long wishListID){
        wishlistRepository.deleteById(wishListID);
    }
}
