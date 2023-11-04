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
public class OrderQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderQuantityID;

    private Integer productQuantity;
    @ManyToOne(optional = false)
    @JoinColumn(name="orderID",nullable = false)
    private Orders order;

    @ManyToOne(optional = false)//unidirectional between the product and order quantity
    @JoinColumn(name = "productID",nullable = false)
    private Products product;
}
