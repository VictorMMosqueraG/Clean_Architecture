package clean.architecture.cleanarchitecture.infrastructure.controller.bases;

import org.springframework.http.ResponseEntity;

public interface BaseController <C, ID, U> {
    
    //C => CreateDto
    //ID => Id
    //U => UpdateDto

    ResponseEntity<?> create(C createDto);
    ResponseEntity<?> findById(ID id);
    ResponseEntity<?> delete(ID id);
    ResponseEntity<?> update(ID id, U updateDto);
}
