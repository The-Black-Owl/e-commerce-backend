package api.backend.mapper;

import api.backend.dto.ProductDTO;
import api.backend.dto.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDto(Products products);

    @Mapping(target = "category", source = "category")
    Products prodRequestToProducts(ProductRequest productRequest);

    default Category mapCategoryStringToCategory(String categoryName) {
        // Assuming you have access to CategoryRepository
        CategoryRepository categoryRepository = null;
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(categoryName);

        return existingCategory.orElseGet(() -> {
            Category newCategory = new Category(categoryName);
            return categoryRepository.save(newCategory);
        });
    }
}
