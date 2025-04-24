package clean.architecture.cleanarchitecture.application.dto.roles;

import clean.architecture.cleanarchitecture.application.dto.base.BasePagination;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public class PaginationRolesDto extends BasePagination{
    
    @Schema(
        description = "Filter roles by name", 
        example = "nameRoles"
    )
    @Size(
        max = 255, 
        message = "Name cannot exceed 100 characters"
    )
    private String name;

  

    //Getters and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
