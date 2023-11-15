package api.backend.exceptions;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id){
        super("Could not find category with id: "+ id+" Status: "+ HttpStatus.NOT_FOUND);
    }
}
