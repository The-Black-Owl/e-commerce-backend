package api.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;
    private String categoryName;

    @OneToMany(mappedBy="category")
    private Set<Products> products= new HashSet<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
