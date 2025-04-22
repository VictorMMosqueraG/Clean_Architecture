package clean.architecture.cleanarchitecture.infrastructure.config.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import clean.architecture.cleanarchitecture.application.cases.roles.CreateRoleCase;
import clean.architecture.cleanarchitecture.application.cases.roles.DeleteRolesCase;
import clean.architecture.cleanarchitecture.application.cases.roles.FindAllRolesCase;
import clean.architecture.cleanarchitecture.application.cases.roles.FindByIdRolesCase;
import clean.architecture.cleanarchitecture.application.cases.roles.UpdateRolesCase;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;
import clean.architecture.cleanarchitecture.infrastructure.mapper.RolesMapper;

@Configuration
public class RolesConfig {
    
    /**
     * This method creates a bean of type CreateRoleCase.
     * It takes a RolesRepository as a parameter and returns a new instance of CreateRoleCase.
     * 
     * @param repository The RolesRepository instance used to access role data.
     * @param rolesMapper The RolesMapper instance used to map between domain objects and data transfer objects (DTOs).
     * 
     * The RolesMapper is used to map between domain objects and data transfer objects (DTOs).
     * This method is annotated with @Bean, which indicates that it is a Spring bean definition.  
     * The Spring framework will manage the lifecycle of this bean and inject it into other components as needed.
     * The CreateRoleCase bean can be used in controllers or other components to handle role creation logic.
     * By using dependency injection, we can easily swap out the implementation of the RolesRepository
     * with a different implementation (e.g., a mock repository for testing) without changing the code in the CreateRoleCase.
     * This promotes loose coupling and makes the code more maintainable and testable.
    */
    @Bean
    public CreateRoleCase createRoleCase(
        RolesRepository repository,
        RolesMapper rolesMapper
    ){
        return new CreateRoleCase(repository, rolesMapper);
    }

    /**
     * This method creates a bean of type FindByIdRolesCase.
     * It takes a RolesRepository as a parameter and returns a new instance of FindByIdRolesCase.
     *  
     * @param repository The RolesRepository instance used to access role data.
     * 
     * The FindByIdRolesCase bean can be used in controllers or other components to handle role retrieval logic.
     * By using dependency injection, we can easily swap out the implementation of the RolesRepository
     * with a different implementation (e.g., a mock repository for testing) without changing the code in the FindByIdRolesCase.
     * This promotes loose coupling and makes the code more maintainable and testable.
    */
    @Bean
    public FindByIdRolesCase findByIDRoleCase(
        RolesRepository repository
    ){
        return new FindByIdRolesCase(repository);
    }

    /**
     * This method creates a bean of type DeleteRolesCase.
     * It takes a RolesRepository as a parameter and returns a new instance of DeleteRolesCase.
     * 
     * @param repository The RolesRepository instance used to access role data.
     * 
     * The DeleteRolesCase bean can be used in controllers or other components to handle role deletion logic.
     * By using dependency injection, we can easily swap out the implementation of the RolesRepository
     * with a different implementation (e.g., a mock repository for testing) without changing the code in the DeleteRolesCase.
     * This promotes loose coupling and makes the code more maintainable and testable.
    */
    @Bean
    public DeleteRolesCase deleteRolesCase(
        RolesRepository repository
    ){
        return new DeleteRolesCase(repository);
    }

    /**
     * This method a creates a bean of type UpdateRolesCase.
     * It takes a RolesRepository as a parameter and returns a new instance of UpdateRolesCase
     * 
     * @param repository The RolesRepository instance used to access book data.
     * @param rolesMapper The RolesMapper instance used to map between domain object and data transfer object (DTOs)
     * 
     * The UpdateRolesCase is a use case that handle update of Roles.
      * This method is annotated with @Bean, which indicates that it is a Spring bean definition.
     * The Spring framework will manage the lifecycle of this bean and inject it into other components as needed.
     * The UpdateRolesCase bean can be used in controllers or other components to handle book deletion logic.
     * By using dependency injection, we can easily swap out the implementation of the BookRepository
     * with a different implementation (e.g., a mock repository for testing) without changing the code in the UpdateRolesCase.
     * This promotes loose coupling and makes the code more maintainable and testable. 
     * 
    */
    @Bean
    public UpdateRolesCase updateRolesCase(
        RolesRepository repository,
        RolesMapper rolesMapper
    ){
        return new UpdateRolesCase(repository, rolesMapper);
    }

    /**
     * Creates a bean of type FindAllRolesCase.
     * This method is responsible for creating an instance of FindAllRolesCase, 
     * which handles the logic of retrieving all roles from the system.
     * 
     * @param repository The RolesRepository instance used to access role data.
     * 
     * @return A new instance of FindAllRolesCase that can be used to retrieve all roles.
     * 
     * The FindAllRolesCase bean can be used in controllers or other components to handle role retrieval logic. 
     * By using dependency injection, we can easily swap out the implementation of the RolesRepository 
     * with a different implementation (e.g., a mock repository for testing) without changing the code in the FindAllRolesCase.
     * This promotes loose coupling and makes the code more maintainable and testable.
    */
    @Bean FindAllRolesCase findAllRolesCase(
        RolesRepository repository
    ){
        return new FindAllRolesCase(repository);
    }
}
