package api.backend.services;

import api.backend.dto.product.ProductDTO;
import api.backend.dto.requestRecords.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.exceptions.ResourceNotFoundException;
import api.backend.mapper.ProductsDTOmapper;
import api.backend.repository.CategoryRepository;
import api.backend.repository.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductsDTOmapper productMapper;
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductsDTOmapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    //method to Add a product
    public Products newProduct(ProductRequest productRequest) {
        //find category by name/id
        Category productCategory=categoryRepository.findByCategoryName(productRequest.category())
                .orElse(new Category(productRequest.category()));
        //check if the product already exists
        Optional<Products> productSKU=productRepository.findBySKU(productRequest.SKU());
        if (productSKU.isPresent()){
            throw new ResourceNotFoundException(HttpStatus.FOUND.toString());
        }
        //List of products associated with the category
        Set<Products> products=new HashSet<>();

        //create a new product
        Products product=new Products(productRequest.SKU(),
                productRequest.productName()
                ,productRequest.productDescription()
                ,productRequest.price()
                ,productCategory);

        productRepository.save(product);

        product.setCategory(productCategory);
        products.add(product);

        productCategory.setProducts(products);

        return product;
    }

    //method to get all products
    public List<ProductDTO> allProducts(){
        return productRepository.findAll().stream().map(productMapper).collect(Collectors.toList());
    }

    //method to get product by name
    public List<ProductDTO> productsByCategory(String categoryName){
        Optional<Category> category=categoryRepository.findByCategoryName(categoryName);
         return productRepository.findAllByCategory(category)
                 .stream().map(productMapper).collect(Collectors.toList());
    }

    //method to remove product
    public void deleteProduct(Long sku) {
        //find product by skuNumber
        if(productRepository.findBySKU(sku).isPresent()){
            productRepository.deleteBySKU(sku);
        }
        throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.toString());
    }

    //method to update a product
    public Optional<ProductDTO> updateProduct(Long sku, ProductRequest request) {
        return productRepository.findBySKU(sku).map(updatedProduct->{
            updatedProduct.setProductName(request.productName());
            updatedProduct.setProductDescription(request.productDescription());
            updatedProduct.setPrice(request.price());
            return updatedProduct;
        }).map(productMapper);
    }

}
