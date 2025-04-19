package clean.architecture.cleanarchitecture.infrastructure.enums;

public enum ApiResponseStatus {
    
    // Book 
    BOOK_CREATE_SUCCESS("Book create successfully", 201),
    BOOK_DELETE_SUCCESS("Book delete successfully", 200),
    BOOK_UPDATE_SUCCESS("Book update successfully", 200),

    // Role
    ROLE_CREATE_SUCCESS("Role create successfully", 201),
    ROLE_DELETE_SUCCESS("Role delete successfully", 200);
    
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
