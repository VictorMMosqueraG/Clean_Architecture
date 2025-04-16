package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clean.architecture.cleanarchitecture.application.cases.CreateBookCase;
import clean.architecture.cleanarchitecture.application.dto.CreateBookDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("book")
public class BookController {
    
    private final CreateBookCase createBookCase;

    public BookController(CreateBookCase createBookCase) {
        this.createBookCase = createBookCase;
    }

    @PostMapping()
    public ResponseEntity<?> createBook(
        @RequestBody CreateBookDto dto
    ) {
        // Call the use case to create a book
        createBookCase.createBook(dto);
        
        // Return a response indicating success
        return ResponseEntity.ok("Book created successfully");
    }
    
}
