package clean.architecture.cleanarchitecture.infrastructure.controller.bases;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

public interface BaseController <C, ID, U> {
    
    //C => CreateDto
    //ID => Id
    //U => UpdateDto

    ResponseEntity<?> create(
        @Valid 
        @RequestBody 
        C createDto
    );
    ResponseEntity<?> findById(
        @PathVariable 
        ID id
    );
    ResponseEntity<?> delete(
        @PathVariable 
        ID id
    );
    ResponseEntity<?> update(
        @PathVariable ID id,
        @Valid 
        @RequestBody U updateDto
    );
    ResponseEntity<?> findAll();
}
