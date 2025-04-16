package clean.architecture.cleanarchitecture.infrastructure.repository.roles;

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
    
}
