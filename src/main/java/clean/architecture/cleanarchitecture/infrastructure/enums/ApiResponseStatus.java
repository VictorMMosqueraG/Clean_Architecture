package clean.architecture.cleanarchitecture.infrastructure.enums;

public enum ApiResponseStatus {
    
    // Book 
    BOOK_CREATE_SUCCESS("Book create successfully", 201),

    // Role
    ROLE_CREATE_SUCCESS("Role create successfully", 201);
    
    private final String message;
    private final int status;

    // Constructor
    ApiResponseStatus(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // Getters 
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
