package api.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne(optional = false)//unidirectional mapping
    @JoinColumn(name = "userID",nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name="addressID",nullable = false)
    private Address address;

    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<OrderQuantity> orderQuantity=new ArrayList<>();
}
