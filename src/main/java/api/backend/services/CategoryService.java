package api.backend.services;

import api.backend.dto.requestRecords.CategoryRequest;
import api.backend.entities.Category;
import api.backend.exceptions.CategoryNotFoundException;
import api.backend.exceptions.ResourceNotFoundException;
import api.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    //create a category
    public Category createCategory(CategoryRequest categoryRequest){
        Category category=new Category(categoryRequest.categoryName());
        categoryRepository.save(category);
        return category;
    }
    //find all a category
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    //find a category by Name
    public Optional<Category> getACategory(String categoryRequest) throws ResourceNotFoundException {
        return categoryRepository.findByCategoryName(categoryRequest);
    }
    //update a category
    public Optional<Category> updateCategory(Long id, CategoryRequest request) throws ResourceNotFoundException {
        return Optional.of(categoryRepository.findById(id)
                .map(category -> {
                    category.setCategoryName(request.categoryName());
                    return category;
                }).orElseGet(() -> {
                    Category category = new Category(request.categoryName());
                    return categoryRepository.save(category);
                }));
    }
    //delete category
    public String deleteCategory(Long categoryId){
        if(categoryRepository.findById(categoryId).isEmpty())throw new CategoryNotFoundException(categoryId);
        categoryRepository.deleteById(categoryId);
        return "Successfully removed!";
    }
}
