package api.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long cartID;
    private Date createdAt;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Products products;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public Cart(int quantity, Products products, User user) {
        this.quantity = quantity;
        this.products = products;
        this.user = user;
        this.createdAt=new Date();
    }
}
