package clean.architecture.cleanarchitecture.infrastructure.exception;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import clean.architecture.cleanarchitecture.infrastructure.enums.ApiErrorMessage;
import clean.architecture.cleanarchitecture.infrastructure.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
 
    /**
     * Method to handle Validation exceptions (NotBlanc, NotNull, etc...),
     * and return a custom error response.
     * 
     * @param ex The MethodArgumentNotValidException that was thrown.
     * 
     * @param request The HttpServletRequest object.
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
        MethodArgumentNotValidException ex,
        HttpServletRequest request
    ){
        // Get message errors
        String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(" | "));

        // Create ApiErrorResponse object
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
            ApiErrorMessage.VALIDATION_ERROR.getStatus(),
            ApiErrorMessage.VALIDATION_ERROR.getMessage(),
            message,
            request.getRequestURI()
        );

        // Return ResponseEntity with ApiErrorResponse
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    /**
     * Method to handle DataIntegrityViolationException(Already exists),
     * and return a custom error response (Conflict).
     * 
     * @param ex The DataIntegrityViolationException that was thrown.
     * 
     * @param request The HttpServletRequest object.
    */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(
        DataIntegrityViolationException ex,
        HttpServletRequest request
    ) {

        //Extract message 
        String rawMessage = ex.getRootCause() != null
        ? ex.getRootCause().getMessage(): ex.getMessage();

        // Check if the message contains "Key" and extract the useful part
        String usefulMessage = rawMessage.contains("Key")
        ? rawMessage.substring(rawMessage.indexOf("Key"))
        : rawMessage;

        // Create ApiErrorResponse object
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
            ApiErrorMessage.DUPLICATE_RESOURCE.getStatus(),
            ApiErrorMessage.DUPLICATE_RESOURCE.getMessage(),
            usefulMessage,
            request.getRequestURI()
        );

        // Return ResponseEntity with ApiErrorResponse
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorResponse);
    }

    /**
     * Method to handle NoSuchElementException (Resource not found),
     * and return a custom error response (Not Found).
     * 
     * @param ex The NoSuchElementException that was thrown.
     * @param request The HttpServletRequest object.
    */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(
        NoSuchElementException ex,
        HttpServletRequest request
    ) {
        // Create ApiErrorResponse object
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
            ApiErrorMessage.RESOURCE_NOT_FOUND.getStatus(),
            ApiErrorMessage.RESOURCE_NOT_FOUND.getMessage(),
            ex.getMessage(),
            request.getRequestURI()
        );

        // Return ResponseEntity with ApiErrorResponse
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    /**
     * Method to handle IllegalArgumentException (e.g., bad pagination usage),
     * and return a custom error response (Bad Request).
     *
     * @param ex The IllegalArgumentException that was thrown.
     * @param request The HttpServletRequest object.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(
        IllegalArgumentException ex,
        HttpServletRequest request
    ) {
        // Create ApiErrorResponse object
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
            ApiErrorMessage.INVALID_FILTER_COMBINATION.getStatus(), 
            ApiErrorMessage.INVALID_FILTER_COMBINATION.getMessage(),
            ex.getMessage(),
            request.getRequestURI()
        );
    
        // Return ResponseEntity with ApiErrorResponse
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    /*
     * Method to handle all other exceptions 
     * and return a custom error response (Internal Server Error).
     * 
     * @param ex The Exception that was thrown.
     * 
     * @param request The HttpServletRequest object.
    */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleInternalServerException(
        Exception ex,
        HttpServletRequest request
    ){

        // Create ApiErrorResponse object
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
            ApiErrorMessage.INTERNAL_SERVER_ERROR.getStatus(),
            ApiErrorMessage.INTERNAL_SERVER_ERROR.getMessage(),
            ex.getMessage(),
            request.getRequestURI()
        );

        // Return ResponseEntity with ApiErrorResponse
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
    }



}
