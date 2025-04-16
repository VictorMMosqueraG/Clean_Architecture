package clean.architecture.cleanarchitecture.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import clean.architecture.cleanarchitecture.application.cases.book.CreateBookCase;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;

@Configuration
public class BookConfig {
    

    /**
     * * This method creates a bean of type CreateBookCase.
     * * It takes a BookRepository as a parameter and returns a new instance of CreateBookCase.
     * * The CreateBookCase is a use case that handles the creation of books.
     * * The BookRepository is an interface that defines the methods for accessing book data.
     * * This method is annotated with @Bean, which indicates that it is a Spring bean definition.
     * * * The Spring framework will manage the lifecycle of this bean and inject it into other components as needed.
     * * * The CreateBookCase bean can be used in controllers or other components to handle book creation logic.
     * * * By using dependency injection, we can easily swap out the implementation of the BookRepository
     * * * with a different implementation (e.g., a mock repository for testing) without changing the code in the CreateBookCase.
     * * * This promotes loose coupling and makes the code more maintainable and testable.
    */
    @Bean
    public CreateBookCase createBookCase(BookRepository repository){
        return new CreateBookCase(repository);
    }
}
