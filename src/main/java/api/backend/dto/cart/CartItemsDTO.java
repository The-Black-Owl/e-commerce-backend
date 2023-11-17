package api.backend.dto.cart;

import api.backend.entities.Cart;
import api.backend.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemsDTO {
    private Long cartID;
    private Integer quantity;
    private Products products;

    public CartItemsDTO(Cart cart) {
        this.cartID=cart.getCartID();
        this.products=cart.getProducts();
        this.quantity= cart.getQuantity();
    }
}
