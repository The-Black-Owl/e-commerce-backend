package api.backend.entities;

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
    private Long SKU;//stock unit number, which is a unique identifier for our products
    private String productName;
    private String productDescription;
    private double price;
    @OneToOne(mappedBy = "product",cascade = CascadeType.REMOVE,optional = false,orphanRemoval = true)
    private Inventory inventory;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name="product_ID"),
            inverseJoinColumns = @JoinColumn(name="category_ID")
    )
    private Set<Category> category;

    public Products(Long SKU, String productName, String productDescription,double price,Set<Category>  category) {
        this.SKU = SKU;
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.price=price;
    }
}
