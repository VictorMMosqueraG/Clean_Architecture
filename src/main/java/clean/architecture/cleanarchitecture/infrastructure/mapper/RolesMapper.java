package clean.architecture.cleanarchitecture.infrastructure.mapper;

import org.springframework.stereotype.Component;

import clean.architecture.cleanarchitecture.application.dto.roles.CreateRolesDto;
import clean.architecture.cleanarchitecture.application.dto.roles.UpdateRolesDto;
import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.infrastructure.entity.RolesEntity;
import clean.architecture.cleanarchitecture.infrastructure.mapper.base.GenericMapper;

@Component
public class RolesMapper implements GenericMapper<RolesEntity, RolesModel, CreateRolesDto> {
    
    /**
     * Method to convert a RolesModel object to a RolesEntity object,
     * take the RolesMOdel object and convert it to a RolesEntity
     * 
     * @param RolesModel roles - Object to be convert
     * 
     * @return RolesEntity - Convert object
    */
    @Override
    public RolesEntity modelToEntity(RolesModel roles){
        //Create a new RolesEntity object
        RolesEntity rolesEntity = new RolesEntity();

        //Set the properties of the RolesEntity object
        rolesEntity.setId(roles.getId());
        rolesEntity.setName(roles.getName());
        rolesEntity.setDescription(roles.getDescription());

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
    @Override
    public RolesModel entityToModel(RolesEntity rolesEntity){
        //Create a new RolesModel object
        RolesModel roles = new RolesModel();

        //Set the properties of the RolesModel object
        roles.setId(rolesEntity.getId());
        roles.setName(rolesEntity.getName());
        roles.setDescription(rolesEntity.getDescription());

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
    @Override
    public RolesModel dtoCreateToModel(CreateRolesDto dto) {
        //Create a new RolesModel object
        RolesModel roles = new RolesModel();

        //Set the properties of the RolesModel object
        roles.setName(dto.getName());
        roles.setDescription(dto.getDescription());

        return roles;
    }

    /**
     * Method to convert a UpdateROlesDto object to a RolesModel object,
     * it take the UpdateRolesDto and convert it a RolesModel.
     * 
     * @param UpdateRolesDto dto - Object to be convert
     * 
     * @return RolesModel - Convert object
    */
    public RolesModel DtoToUpdateToModel(UpdateRolesDto dto){
        //Create a new RolesModel object
        RolesModel roles = new RolesModel();

        //set the properties
        roles.setName(dto.getName());
        roles.setDescription(dto.getDescription());

        return roles;
    }

}
