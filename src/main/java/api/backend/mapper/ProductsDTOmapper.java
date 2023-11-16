package api.backend.mapper;

import api.backend.dto.ProductDTO;
import api.backend.entities.Products;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductsDTOmapper implements Function<Products, ProductDTO> {
    @Override
    public ProductDTO apply(Products products) {
        return new ProductDTO(
                products.getProductName(),
                products.getSKU(),
                products.getProductDescription(),
                products.getCategory().getCategoryName(),
                products.getPrice()
        );
    }
}
