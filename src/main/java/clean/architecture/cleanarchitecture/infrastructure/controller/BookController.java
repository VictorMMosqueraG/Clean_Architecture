package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clean.architecture.cleanarchitecture.application.cases.book.CreateBookCase;
import clean.architecture.cleanarchitecture.application.dto.book.CreateBookDto;
import clean.architecture.cleanarchitecture.infrastructure.enums.ApiResponseStatus;
import clean.architecture.cleanarchitecture.infrastructure.response.ApiResponseData;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("book")
@Tag(name = "Book")
@Validated
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
    @Operation(
        summary = "Create a new Book",
        description = "Create a new Book."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Book created successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"message\": \"Book created successfully\", \"status\": 201}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Duplicate resource found. It may already exist.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 409, \"message\": \"Duplicate resource found. It may already exist.\", \"error\": \"Key (tittle)=(Book Tittle) already exists.\", \"path\": \"/book\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error occurred. Please check your input.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 400, \"message\": \"Validation error occurred. Please check your input.\", \"error\": \"tittle: Tittle is required, it cannot be blank or null\", \"path\": \"/book\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 500, \"message\": \"An unexpected error occurred. Please try again later.\", \"error\": \"An unexpected error occurred. Please try again later.\", \"path\": \"/book\"}")
                )
            }
        )
    })
    @PostMapping()
    public ResponseEntity<?> createBook(
        @Valid
        @RequestBody CreateBookDto dto
    ) {
        // Call the use case to create a book
        createBookCase.createBook(dto);
        
        // Return a response indicating success
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponseData(
                ApiResponseStatus.BOOK_CREATE_SUCCESS.getMessage(), 
                ApiResponseStatus.BOOK_CREATE_SUCCESS.getStatus())
            );
    }
    
}
