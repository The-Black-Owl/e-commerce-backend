package api.backend.services;

import api.backend.dto.ProductDTO;
import api.backend.dto.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.mapper.ProductMapper;
import api.backend.repository.CategoryRepository;
import api.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;

    //method to Add a product
    public ProductDTO newProduct(ProductRequest productRequest) {
        //check if the product already exists
        Optional<Products> productSKU=productRepository.findBySKU(productRequest.SKU());
        if (productSKU.isPresent()){
            throw new RuntimeException(HttpStatus.FOUND.toString());
        }
        //create a category
        Category category=new Category(productRequest.category());
        HashSet<Category> productCategory=new HashSet<>();
        productCategory.add(category);
        categoryRepository.save(category);
        //create a new product
        Products products=productMapper.prodRequestToProducts(productRequest);
        products.setCategory(productCategory);

        Products newProduct=productRepository.save(products);
        return productMapper.toProductDto(newProduct);
    }

    //method to get all products
    public List<ProductDTO> allProducts(){
        List<Products> products=new ArrayList<>();
        productRepository.findAll().forEach(prod->products.add(prod));
        List<ProductDTO> productDTOs=new ArrayList<>();
        for(Products prod:products){
            productDTOs.add(productMapper.toProductDto(prod));
        }
        return productDTOs;
    }

    //method to get product by name
    public List<ProductDTO> productsByCategory(String categoryName){
        List<Category> category=categoryRepository.findAllByCategoryName(categoryName);
        List<ProductDTO> productDTOS=new ArrayList<>();
        List<Products> productsList=productRepository.findAllByCategory(category.get(0));
        for(Products prod:productsList){
            productDTOS.add(productMapper.toProductDto(prod));
        }
        return productDTOS;
    }

    //method to remove product
    public void deleteProduct(Long sku) {
        //find product by skuNumber
        if(productRepository.findBySKU(sku).isPresent()){
            productRepository.deleteBySKU(sku);
        }
        throw new RuntimeException(HttpStatus.NOT_FOUND.toString());
    }

    //method to update a product
    public ProductDTO updateProduct(Long sku, ProductRequest request) {
        Optional<Products> findProduct=productRepository.findBySKU(sku);
        if(!findProduct.isPresent()){
            throw new RuntimeException(HttpStatus.NOT_FOUND.toString());
        }
        Products products=productMapper.prodRequestToProducts(request);
        products.setProductName(request.productName());
        products.setProductDescription(request.productDescription());

        Products updatedProduct=productRepository.save(products);
        return productMapper.toProductDto(updatedProduct);
    }

}
