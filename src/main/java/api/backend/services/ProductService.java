package api.backend.services;

import api.backend.dto.requestRecords.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.exceptions.ResourceNotFoundException;
import api.backend.mapper.ProductMapper;
import api.backend.repository.CategoryRepository;
import api.backend.repository.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    //method to Add a product
    public Products newProduct(ProductRequest productRequest) {
        //check if the product already exists
        Optional<Products> productSKU=productRepository.findBySKU(productRequest.SKU());
        if (productSKU.isPresent()){
            throw new ResourceNotFoundException(HttpStatus.FOUND.toString());
        }
        //create a category
        Category productCategory=new Category(productRequest.category());
        categoryRepository.save(productCategory);
        //create a new product
        Products product=new Products(productRequest.SKU(),
                productRequest.productName()
                ,productRequest.productDescription()
                ,productRequest.price()
                ,productCategory);
         productRepository.save(product);
        return product;
    }

    //method to get all products
    public List<Products> allProducts(){
        return new ArrayList<>(productRepository.findAll());
    }

    //method to get product by name
    public List<Products> productsByCategory(String categoryName){
        Optional<Category> category=categoryRepository.findByCategoryName(categoryName);
         return productRepository.findAllByCategory(category);
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
    public Products updateProduct(Long sku, ProductRequest request) {
        Optional<Products> findProduct=productRepository.findBySKU(sku);
        if(findProduct.isEmpty()){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.toString());
        }
        Products products=productMapper.prodRequestToProducts(request);
        products.setProductName(request.productName());
        products.setProductDescription(request.productDescription());
        productRepository.save(products);
        return products;
    }

}
