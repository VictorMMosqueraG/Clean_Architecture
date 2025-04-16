package clean.architecture.cleanarchitecture.application.dto.book;

public class CreateBookDto {
    
    private String tittle;
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
