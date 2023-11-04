package api.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryID;
    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "productID",nullable = false,unique = true)
    private Products product;
    private Integer quantity;
}
