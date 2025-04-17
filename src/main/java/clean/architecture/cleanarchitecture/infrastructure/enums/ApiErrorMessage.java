package clean.architecture.cleanarchitecture.infrastructure.enums;

public enum ApiErrorMessage {
    
    INTERNAL_SERVER_ERROR(
        "An unexpected error occurred. Please try again later.", 
        500
    ),
    
    VALIDATION_ERROR(
        "Validation error occurred. Please check your input.", 
        400
    ),

    DUPLICATE_RESOURCE(
        "Duplicate resource found. It may already exist.",
        409
    );


    private final String message;
    private final int status;

    //Constructor
    ApiErrorMessage(String message, int status) {
        this.message = message;
        this.status = status;
    }
    
    //Getters
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
