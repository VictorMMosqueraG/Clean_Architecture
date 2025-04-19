package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clean.architecture.cleanarchitecture.application.cases.book.CreateBookCase;
import clean.architecture.cleanarchitecture.application.cases.book.DeleteBookCase;
import clean.architecture.cleanarchitecture.application.cases.book.FindByIDBookCase;
import clean.architecture.cleanarchitecture.application.cases.book.UpdateBookCase;
import clean.architecture.cleanarchitecture.application.dto.book.CreateBookDto;
import clean.architecture.cleanarchitecture.application.dto.book.UpdateBookDto;
import clean.architecture.cleanarchitecture.infrastructure.controller.bases.BaseController;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("book")
@Tag(name = "Book")
@Validated
public class BookController implements BaseController<CreateBookDto, Integer, UpdateBookDto> {
    
    private final CreateBookCase createBookCase;
    private final FindByIDBookCase findByIDBookCase;
    private final DeleteBookCase deleteBookCase;
    private final UpdateBookCase updateBookCase;


    public BookController(
        CreateBookCase createBookCase,
        FindByIDBookCase findByIDBookCase,
        DeleteBookCase deleteBookCase,
        UpdateBookCase updateBookCase
    ) {
        this.createBookCase = createBookCase;
        this.findByIDBookCase = findByIDBookCase;
        this.deleteBookCase = deleteBookCase;
        this.updateBookCase = updateBookCase;
    }

    /**
     * Method to create a new Book in the system,
     * It receives a CreateBookDto object in the request body,
     * which contains the necessary information to create a new book.
     * It calls the createBookCase to handle the business logic of creating a book.
     * 
     * @param createDto CreateBookDto object containing the book information
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
    public ResponseEntity<?> create(
        @Valid
        @RequestBody CreateBookDto createDto
    ) {
        // Call the use case to create a book
        createBookCase.createBook(createDto);
        
        // Return a response indicating success
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponseData(
                ApiResponseStatus.BOOK_CREATE_SUCCESS.getMessage(), 
                ApiResponseStatus.BOOK_CREATE_SUCCESS.getStatus())
            );
    }

    /**
     * Method to find by id a book in the system,
     * it take an id and calling the method findById from the use case
     * FindByIDBookCase to retrieve the book information.
     * 
     * @param id Integer representing the ID of the book to be retrieved
     * 
     * @return ResponseEntity with the book information and HTTP status code
    */

    @Operation(
        summary = "Find a Book by ID",
        description = "Find a Book by ID."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "book found successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                example = "{ \"id\": 1, \"name\": \"testBook\", \"description\": \"descriptionBook\" }"
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Book not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 404, \"message\": \"Resource not found. Please check the resource ID..\", \"error\": \"Book not found\", \"path\": \"/book/{id}\"}")
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
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(findByIDBookCase.findById(id));//Call the method FindByID
    }

    /**
     * This method is to delete a book from the system,
     * it take an id and calling the method deleteBook from the use case
     * 
     * @param id Integer representing the ID of the book to be deleted
     * 
     * @return ResponseEntity with a success message and HTTP status code
    */
    @Operation(
        summary = "Delete a Book by ID",
        description = "Delete a Book by ID."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Book deleted successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"message\": \"Book deleted successfully\", \"status\": 200}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Book not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 404, \"message\": \"Resource not found. Please check the resource ID..\", \"error\": \"Book not found\", \"path\": \"/book/{id}\"}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        // Call the use case to delete a book
        deleteBookCase.deleteBookCase(id);
        
        // Return a response indicating success
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ApiResponseData(
                ApiResponseStatus.BOOK_DELETE_SUCCESS.getMessage(), 
                ApiResponseStatus.BOOK_DELETE_SUCCESS.getStatus())
            );
    }


    /**
     * Method to update a book entity,
     * it receives a UpdateBookDto object in the request body,
     * and it receives an id in the path.
     * It calling the updateBookCase method from use case
     * 
     * @param Integer id
     * @param UpdateBookDto updateBookDto
     * 
     * @return ResponseEntity with a success message and Http status code
    */
    @Operation(
        summary = "Update a new Book",
        description = "Update a new Book."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Update created successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"message\": \"Book update successfully\", \"status\": 200}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Duplicate resource found. It may already exist.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 409, \"message\": \"Duplicate resource found. It may already exist.\", \"error\": \"Key (tittle)=(Book Tittle update) already exists.\", \"path\": \"/book/{id}\"}")
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
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id, 
        @Valid
        @RequestBody UpdateBookDto dto
    ) {
        //call the use case to update a book
        updateBookCase.updateBookCase(id, dto);
        
       return ResponseEntity
       .status(HttpStatus.OK)
       .body(new ApiResponseData(
            ApiResponseStatus.BOOK_UPDATE_SUCCESS.getMessage(), 
            ApiResponseStatus.BOOK_UPDATE_SUCCESS.getStatus())
        );
    }

    
}
