package api.backend.mapper;

import api.backend.dto.ProductDTO;
import api.backend.dto.requestRecords.ProductRequest;
import api.backend.entities.Products;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T14:55:13+0200",
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
        productDTO.category( products.getCategory() );
        productDTO.price( products.getPrice() );

        return productDTO.build();
    }

    @Override
    public Products prodRequestToProducts(ProductRequest productRequest) {
        if ( productRequest == null ) {
            return null;
        }

        Products products = new Products();

        products.setCategory( mapCategoryStringToCategory( productRequest.category() ) );
        products.setSKU( productRequest.SKU() );
        products.setProductName( productRequest.productName() );
        products.setProductDescription( productRequest.productDescription() );
        products.setPrice( productRequest.price() );

        return products;
    }
}
