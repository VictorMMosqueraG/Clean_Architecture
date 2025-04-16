package clean.architecture.cleanarchitecture.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import clean.architecture.cleanarchitecture.application.cases.roles.CreateRoleCase;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;

@Configuration
public class RolesConfig {
    
    /**
     * * This method creates a bean of type CreateRoleCase.
     * * It takes a RolesRepository as a parameter and returns a new instance of CreateRoleCase.
     * * The CreateRoleCase is a use case that handles the creation of roles.
     * * The RolesRepository is an interface that defines the methods for accessing role data.
     * * This method is annotated with @Bean, which indicates that it is a Spring bean definition.  
     * * * The Spring framework will manage the lifecycle of this bean and inject it into other components as needed.
     * * * The CreateRoleCase bean can be used in controllers or other components to handle role creation logic.
     * * * By using dependency injection, we can easily swap out the implementation of the RolesRepository
     * * * with a different implementation (e.g., a mock repository for testing) without changing the code in the CreateRoleCase.
     * * * This promotes loose coupling and makes the code more maintainable and testable.
    */
    @Bean
    public CreateRoleCase createRoleCase(RolesRepository repository){
        return new CreateRoleCase(repository);
    }
}
