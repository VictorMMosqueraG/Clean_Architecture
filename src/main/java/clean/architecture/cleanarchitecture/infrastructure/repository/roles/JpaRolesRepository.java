package clean.architecture.cleanarchitecture.infrastructure.repository.roles;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Repository;

import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;
import clean.architecture.cleanarchitecture.infrastructure.entity.RolesEntity;
import clean.architecture.cleanarchitecture.infrastructure.mapper.RolesMapper;

@Repository
public class JpaRolesRepository implements RolesRepository {

    private final SpringDataRolesRepository repository;
    private final RolesMapper rolesMapper;

    public JpaRolesRepository(
        SpringDataRolesRepository repository,
        RolesMapper rolesMapper
    ) {
        this.repository = repository;
        this.rolesMapper = rolesMapper;
    }

    /**
     * Method to create a new Role,
     * take the RolesModel object and convert it to RolesEntity object,
     * then calling the method save from the repository
     * 
     * @param role RolesModel object
     * 
     * @return void
    */
    @Override
    public void createRole(RolesModel role) {
        //Convert RolesModel to RolesEntity
        RolesEntity rolesEntity = rolesMapper.modelToEntity(role);

        //Save the roles
        repository.save(rolesEntity);
    }


    /*
     * Method to get roles by id,
     * it take the id of the role and call the method finById from the repository,
     * if the role is not found it will throw a NoSuchElementException,
     * if the role is found it will convert the RolesEntity to RolesModel
     * 
     * @param Integer id 
     * 
     * @return RolesModel 
    */
    @Override
    public RolesModel getRolesById(Integer id) {
        //Get the roles by id
        RolesEntity rolesEntity = repository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Role not found"));

        //Convert RolesEntity to RolesModel
        return rolesMapper.entityToModel(rolesEntity);
    }

    /**
     * Method to delete a role by id,
     * it take an id and find the roles in the system,
     * if the roles is not found it will throw a NoSuchElementException,
     * if the roles is found it will delete the roles
     * 
     * @param Integer id
     * 
     * @return void
    */
    @Override
    public void deleteRoles(Integer id) {
        //Valid if the roles exist
        this.getRolesById(id);

        //Delete role by id
        repository.deleteById(id);
    }

    
    @Override
    public RolesModel updateRoles(Integer id, RolesModel rolesModel) {

        //Find roles by id
        RolesModel foundRoles = this.getRolesById(id);

        //set values
        foundRoles.setName(rolesModel.getName());
        foundRoles.setDescription(rolesModel.getDescription());

        //Convert to model to entity
        RolesEntity rolesEntity = rolesMapper.modelToEntity(foundRoles);

        //calling method save from repository
        repository.save(rolesEntity);

        //Convert to entity to model and return
        return rolesMapper.entityToModel(rolesEntity);
    }
    
}
