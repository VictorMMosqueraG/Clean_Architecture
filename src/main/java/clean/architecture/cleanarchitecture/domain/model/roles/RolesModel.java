package clean.architecture.cleanarchitecture.domain.model.roles;

import java.util.Optional;

import clean.architecture.cleanarchitecture.application.dto.roles.UpdateRolesDto;

public class RolesModel {

    private int id;
    private String name;
    private String description;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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


    /**
     * Method to valid files if null or not null,
     * if not null it will set values,
     * if is null it don't set values
     * 
     * @param UpdateRolesDto dto
     * 
     * @return void
    */
    public void updateFields(UpdateRolesDto dto){
        Optional.ofNullable(dto.getName()).ifPresent(this::setName);
        Optional.ofNullable(dto.getDescription()).ifPresent(this::setDescription);
    }
    
}
