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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private boolean isActive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
