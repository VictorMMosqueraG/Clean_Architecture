package clean.architecture.cleanarchitecture.infrastructure.response;

public class ApiResponseData {
    
    private String message;
    private int status;

    // Constructor
    public ApiResponseData(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

