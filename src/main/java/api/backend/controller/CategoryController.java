package api.backend.controller;

import api.backend.dto.requestRecords.CategoryRequest;
import api.backend.entities.Category;
import api.backend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/category/searchAll")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/category/search/{categoryName}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable("categoryName") String categoryName){
        return ResponseEntity.ok(categoryService.getACategory(categoryName));
    }
    @PostMapping("/category/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest){
        Category category=categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/category/update/{categoryID}")
    public ResponseEntity<Optional<Category>> updateCategory(@PathVariable("categoryID") Long id, @RequestBody CategoryRequest request){
        Optional<Category> updateCategory=categoryService.updateCategory(id,request);
        return ResponseEntity.ok(updateCategory);
    }
    @DeleteMapping("/category/delete/{categoryID}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryID") Long id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
