package clean.architecture.cleanarchitecture.infrastructure.mapper;

import clean.architecture.cleanarchitecture.application.dto.roles.CreateRolesDto;
import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.infrastructure.entity.RolesEntity;

public class RolesMapper {
    
    /**
     * Method to convert a RolesModel object to a RolesEntity object,
     * take the RolesMOdel object and convert it to a RolesEntity
     * 
     * @param RolesModel roles - Object to be convert
     * 
     * @return RolesEntity - Convert object
    */
    public static RolesEntity modelToEntity(RolesModel roles){
        //Create a new RolesEntity object
        RolesEntity rolesEntity = new RolesEntity();

        //Set the properties of the RolesEntity object
        rolesEntity.setId(roles.getId());
        rolesEntity.setName(roles.getName());

        return rolesEntity;
    }

    /**
     * Method to convert a RolesEntity object to a RolesModel object,
     * take the RolesEntity object and convert it to a RolesModel
     * 
     * @param RolesEntity rolesEntity - Object to be convert
     * 
     * @return RolesModel - Convert object
    */
    public static RolesModel entityToModel(RolesEntity rolesEntity){
        //Create a new RolesModel object
        RolesModel roles = new RolesModel();

        //Set the properties of the RolesModel object
        roles.setId(rolesEntity.getId());
        roles.setName(rolesEntity.getName());

        return roles;
    }

    /**
     * Method to convert a CreateRolesDto object to a RolesModel object,
     * take the CreateRolesDto object and convert it to a RolesModel
     * 
     * @param CreateRolesDto dto - Object to be convert
     * 
     * @return RolesModel - Convert object
    */
    public static RolesModel dtoToModel(CreateRolesDto dto){
        //Create a new RolesModel object
        RolesModel roles = new RolesModel();

        //Set the properties of the RolesModel object
        roles.setName(dto.getName());

        return roles;
    }

    //FIX: finding a better wat to do this
}
