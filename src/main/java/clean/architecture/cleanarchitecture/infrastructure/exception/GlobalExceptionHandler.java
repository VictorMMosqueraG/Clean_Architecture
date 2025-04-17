package clean.architecture.cleanarchitecture.infrastructure.exception;

import java.util.stream.Collectors;

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
