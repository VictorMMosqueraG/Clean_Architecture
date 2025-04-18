package clean.architecture.cleanarchitecture.domain.repository.roles;

import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;

public interface RolesRepository {
    
    public void createRole(RolesModel role);
    public RolesModel getRolesById(Integer id);
}
