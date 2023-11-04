package api.backend.dto;

import api.backend.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String productName;
    private Long SKU;
    private String productDescription;
    private Set<Category> category;
    double price;
}
