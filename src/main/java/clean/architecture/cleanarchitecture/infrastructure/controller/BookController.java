package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clean.architecture.cleanarchitecture.application.cases.book.CreateBookCase;
import clean.architecture.cleanarchitecture.application.dto.book.CreateBookDto;
import clean.architecture.cleanarchitecture.infrastructure.enums.ApiResponseStatus;
import clean.architecture.cleanarchitecture.infrastructure.response.ApiResponse;


import org.springframework.http.HttpStatus;
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

    /**
     * Method to create a new Book in the system,
     * It receives a CreateBookDto object in the request body,
     * which contains the necessary information to create a new book.
     * It calls the createBookCase to handle the business logic of creating a book.
     * 
     * @param dto CreateBookDto object containing the book information
     * 
     * @return ResponseEntity with a success message and HTTP status code
    */
    @PostMapping()
    public ResponseEntity<?> createBook(
        @RequestBody CreateBookDto dto
    ) {
        // Call the use case to create a book
        createBookCase.createBook(dto);
        
        // Return a response indicating success
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponse(
                ApiResponseStatus.BOOK_CREATE_SUCCESS.getMessage(), 
                ApiResponseStatus.BOOK_CREATE_SUCCESS.getStatus())
            );
    }
    
}
