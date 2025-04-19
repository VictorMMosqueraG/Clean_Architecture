package clean.architecture.cleanarchitecture.application.cases.roles;

import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;

public class DeleteRolesCase {
    
    private final RolesRepository rolesRepository;

    public DeleteRolesCase(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    /**
     * This method is to delete a role by id,
     * it calling the deleteRoles method from the repository.
     * 
     * @param id The ID of the role to be deleted.
     * 
     * @return void
     */
    public void deleteRoles(Integer id){
        //calling the repository method to delete the role by id
        rolesRepository.delete(id);
    }
}
