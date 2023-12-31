package api.backend.repository;

import api.backend.entities.Category;
import api.backend.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
    Optional<Products> findByProductName(String productName);//finds all products by their name
    Optional<Products> findBySKU(Long sku);//find product by the stock unit number
    List<Products> findAllByCategory(Optional<Category> category);
    void deleteBySKU(Long sku);//removes the product by the stock unit number

}
