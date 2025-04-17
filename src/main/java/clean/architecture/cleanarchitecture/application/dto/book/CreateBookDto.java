package clean.architecture.cleanarchitecture.application.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateBookDto {

    @Size(
        min = 5, 
        max = 255, 
        message = "tittle must be between 5 and 255 characters"
    )
    @NotBlank(message = "Tittle is required, it cannot be blank or null")
    @Schema(example = "Book Tittle")
    private String tittle;

    @Size(
        min = 5, 
        max = 255, 
        message = "description must be between 5 and 255 characters"
    )
    @NotBlank(message = "Description is required, it cannot be blank or null")
    @Schema(example = "Book Description")
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
