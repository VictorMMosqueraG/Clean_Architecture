package clean.architecture.cleanarchitecture.application.dto.book;

import clean.architecture.cleanarchitecture.application.dto.base.BasePagination;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public class PaginationBookDto extends BasePagination {
    
    @Schema(
        description = "Filter book by tittle", 
        example = "tittleBook"
    )
    @Size(
        max = 255, 
        message = "Tittle cannot exceed 100 characters"
    )
    private String tittle;

    // Getters and Setters
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    
}
