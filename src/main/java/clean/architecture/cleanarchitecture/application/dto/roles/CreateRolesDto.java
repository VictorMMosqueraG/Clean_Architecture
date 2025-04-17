package clean.architecture.cleanarchitecture.application.dto.roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateRolesDto {
    
    
     @Size(
        min = 5, 
        max = 255, 
        message = "name must be between 5 and 255 characters"
    )
    @NotBlank(message = "Name is required, it cannot be blank or null")
    private String name;

    @NotBlank(message = "description is required, it cannot be blank or null")
    private String description;

    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
