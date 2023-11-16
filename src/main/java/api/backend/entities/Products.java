package api.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @Column(nullable = false,unique = true)
    private Long SKU;
    private String productName;
    private String productDescription;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "categoryID")
    private Category category;
    @OneToMany(mappedBy = "products")
    private Set<Wishlist> wishlists;

    public Products(Long SKU, String productName, String productDescription,double price,Category  category) {
        this.SKU = SKU;
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.price=price;
    }
}
