package api.backend.services;

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
    public Products newProduct(ProductRequest productRequest) {
        //check if the product already exists
        Optional<Products> productSKU=productRepository.findBySKU(productRequest.SKU());
        if (productSKU.isPresent()){
            throw new RuntimeException(HttpStatus.FOUND.toString());
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
//      product.setCategory(productCategory);

        productRepository.save(product);
        return product;
    }

    //method to get all products
    public List<Products> allProducts(){
        List<Products> products=new ArrayList<>();
        productRepository.findAll().forEach(prod->products.add(prod));
        return products;
    }

    //method to get product by name
    public List<Products> productsByCategory(String categoryName){
        Optional<Category> category=categoryRepository.findByCategoryName(categoryName);
        List<Products> productsList=productRepository.findAllByCategory(category);

        return productsList;
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
    public Products updateProduct(Long sku, ProductRequest request) {
        Optional<Products> findProduct=productRepository.findBySKU(sku);
        if(!findProduct.isPresent()){
            throw new RuntimeException(HttpStatus.NOT_FOUND.toString());
        }
        Products products=productMapper.prodRequestToProducts(request);
        products.setProductName(request.productName());
        products.setProductDescription(request.productDescription());

        Products updatedProduct=productRepository.save(products);
        return products;
    }

}
