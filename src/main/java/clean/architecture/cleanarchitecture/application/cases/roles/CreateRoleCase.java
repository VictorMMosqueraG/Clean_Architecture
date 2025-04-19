package clean.architecture.cleanarchitecture.application.cases.roles;

import clean.architecture.cleanarchitecture.application.dto.roles.CreateRolesDto;
import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;
import clean.architecture.cleanarchitecture.infrastructure.mapper.RolesMapper;

public class CreateRoleCase {

    private final RolesRepository repository;
    private final RolesMapper rolesMapper;

    public CreateRoleCase(
        RolesRepository repository,
        RolesMapper rolesMapper
    ) {
        this.repository = repository;
        this.rolesMapper = rolesMapper;
    }

    /**
     * Method to save a new role,
     * it take a CreateRolesDto object 
     * and convert it to a RolesModel object,
     * then it calla the method createRole from the repository
     * 
     * @param dto the data transfer object containing role details
     * 
     * @return void
     * 
    */
    public void createRoles(CreateRolesDto dto){
        // Convert Dto to model
        RolesModel roleModel = rolesMapper.dtoCreateToModel(dto);

        //Save the roles
        repository.createRole(roleModel);    
    }
}
