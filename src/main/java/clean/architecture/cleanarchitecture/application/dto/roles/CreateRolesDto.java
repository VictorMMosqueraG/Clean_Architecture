package clean.architecture.cleanarchitecture.application.dto.roles;

import jakarta.validation.constraints.NotBlank;

public class CreateRolesDto {
    
    
    @NotBlank(message = "Name is required, it cannot be blank or null")
    private String name;

    @NotBlank(message = "Description is required, it cannot be blank or null")
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
