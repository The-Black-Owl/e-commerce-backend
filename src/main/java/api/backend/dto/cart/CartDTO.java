package api.backend.dto.cart;

import java.util.List;


public class CartDTO {
   private List<CartItemsDTO> cartItems;
   private double totalCost;

    public CartDTO(List<CartItemsDTO> cartItemsDTOList, double totalCost) {
        this.cartItems=cartItemsDTOList;
        this.totalCost=totalCost;
    }
}
