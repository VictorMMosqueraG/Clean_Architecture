package clean.architecture.cleanarchitecture.application.dto.book;

import jakarta.validation.constraints.NotBlank;

public class CreateBookDto {

    @NotBlank(message = "Tittle is required, it cannot be blank or null")
    private String tittle;

    @NotBlank(message = "Description is required, it cannot be blank or null")
    private String description;

    // Getters and Setters
    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }    
}
