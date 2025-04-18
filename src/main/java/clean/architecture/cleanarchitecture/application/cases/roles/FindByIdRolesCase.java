package clean.architecture.cleanarchitecture.application.cases.roles;

import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;

public class FindByIdRolesCase {

    private final RolesRepository repository;

    public FindByIdRolesCase(
        RolesRepository repository
    ) {
        this.repository = repository;
    }

    /**
     * Method to find by id a role,
     * it take an id an find by it, then return the role
     * 
     * @param id the id of the role to find
     * 
     * @return the role found
    */
    public RolesModel findById(Integer id) {
        // Find the role by ID and return it
        return repository.getRolesById(id);
    }


}
