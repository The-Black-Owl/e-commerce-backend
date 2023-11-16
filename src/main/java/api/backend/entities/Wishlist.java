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
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistID;
    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "productID")
    private Products products;
    private Date createdAt;

    public Wishlist(User user, Products products) {
        this.user = user;
        this.products = products;
        this.createdAt=new Date();
    }
}
