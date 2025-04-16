package clean.architecture.cleanarchitecture.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import clean.architecture.cleanarchitecture.application.cases.book.CreateBookCase;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;
import clean.architecture.cleanarchitecture.infrastructure.mapper.BookMapper;

@Configuration
public class BookConfig {
    

    /**
     * This method creates a bean of type CreateBookCase.
     * It takes a BookRepository as a parameter and returns a new instance of CreateBookCase.
     * 
     * @param repository The BookRepository instance used to access book data.
     * @param bookMapper The BookMapper instance used to map between domain objects and data transfer objects (DTOs).
     * 
     * The CreateBookCase is a use case that handles the creation of books.
     * The BookMapper is used to map between the domain model and the data transfer object (DTO).
     * This method is annotated with @Bean, which indicates that it is a Spring bean definition.
     * The Spring framework will manage the lifecycle of this bean and inject it into other components as needed.
     * The CreateBookCase bean can be used in controllers or other components to handle book creation logic.
     * By using dependency injection, we can easily swap out the implementation of the BookRepository
     * with a different implementation (e.g., a mock repository for testing) without changing the code in the CreateBookCase.
     * This promotes loose coupling and makes the code more maintainable and testable.
    */
    @Bean
    public CreateBookCase createBookCase(
        BookRepository repository,
        BookMapper bookMapper
    ){
        return new CreateBookCase(repository, bookMapper);
    }
}
