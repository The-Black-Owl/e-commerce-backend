package api.backend.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckOutItemDTO {
    private String productName;
    private int quantity;
    private double price;
    private long productID;
    private long userID;
    //add boolean for checked out => shipping and the shipping address
}
