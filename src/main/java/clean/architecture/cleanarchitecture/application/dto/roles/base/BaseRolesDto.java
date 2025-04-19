package clean.architecture.cleanarchitecture.application.dto.roles.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public  abstract class BaseRolesDto {

    @Size(
        min = 5, 
        max = 255, 
        message = "name must be between 5 and 255 characters"
    )
    @NotBlank(message = "Name is required, it cannot be blank or null")
    @Schema(example = "Role Name")
    private String name;

    @Size(
        min = 5, 
        max = 255, 
        message = "description must be between 5 and 255 characters"
    )
    @NotBlank(message = "description is required, it cannot be blank or null")
    @Schema(example = "Role Description")
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
