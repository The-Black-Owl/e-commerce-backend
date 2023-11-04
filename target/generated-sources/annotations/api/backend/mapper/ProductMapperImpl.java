package api.backend.mapper;

import api.backend.dto.ProductDTO;
import api.backend.dto.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-14T23:32:12+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDto(Products products) {
        if ( products == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.productName( products.getProductName() );
        productDTO.SKU( products.getSKU() );
        productDTO.productDescription( products.getProductDescription() );
        Set<Category> set = products.getCategory();
        if ( set != null ) {
            productDTO.category( new LinkedHashSet<Category>( set ) );
        }

        return productDTO.build();
    }

    @Override
    public Products prodRequestToProducts(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Products products = new Products();

        products.setSKU( request.SKU() );
        products.setProductName( request.productName() );
        products.setProductDescription( request.productDescription() );
        products.setCategory( mapCategoryStringToSet( request.category() ) );

        return products;
    }
}
