package api.backend.mapper;

import api.backend.dto.ProductDTO;
import api.backend.dto.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDto(Products products);

    Products prodRequestToProducts(ProductRequest request);

    //    @Mapping(target = "category", source = "category")
//    Products map(ProductRequest request);
//
//    default Category map(String value) {
//        if (value == null) {
//            return null;
//        }
//        return new Category(value);
//    }
    default Set<Category> mapCategoryStringToSet(String categoryString) {
        Set<Category> categories = new HashSet<>();

        return categories;
    }
}