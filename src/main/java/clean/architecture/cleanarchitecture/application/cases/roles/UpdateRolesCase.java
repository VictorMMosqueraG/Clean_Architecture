package clean.architecture.cleanarchitecture.application.cases.roles;

import clean.architecture.cleanarchitecture.application.dto.roles.UpdateRolesDto;
import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;
import clean.architecture.cleanarchitecture.infrastructure.mapper.RolesMapper;

public class UpdateRolesCase {

    private final RolesRepository repository;
    private final RolesMapper rolesMapper;

    public UpdateRolesCase(
        RolesRepository repository,
        RolesMapper rolesMapper
    ){
        this.repository = repository;
        this.rolesMapper = rolesMapper;
    }

    /**
     * MEthod to update roles,
     * it take a DTO and will convert to model,
     * then calling a updateFiles method from model,
     * then it calling to updateRoles method from repository.
     * 
     * @param id
     * @param updateRolesDto
     * 
     * @return
    */
    public RolesModel updateRolesCase(
        Integer id,
        UpdateRolesDto updateRolesDto
    ){
        //convert Dto to model
        RolesModel rolesModel = rolesMapper.DtoToUpdateToModel(updateRolesDto);

        //valid if the values provide if null or not
        rolesModel.updateFields(updateRolesDto);

        //calling the method to update from repository and return the result
        return repository.update(id, rolesModel);
    }
}
