package clean.architecture.cleanarchitecture.application.cases.roles;

import java.util.List;

import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;

public class FindAllRolesCase {

    private final RolesRepository repository;

    public FindAllRolesCase(RolesRepository repository) {
        this.repository = repository;
    }

    //COMEBACK: Missing documentation
    public List<RolesModel> findAll(){
        return repository.findAll();
    }
}
