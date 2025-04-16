package clean.architecture.cleanarchitecture.infrastructure.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import clean.architecture.cleanarchitecture.domain.model.BookModel;
import clean.architecture.cleanarchitecture.domain.repository.BookRepository;
import clean.architecture.cleanarchitecture.infrastructure.entity.BookEntity;
import clean.architecture.cleanarchitecture.infrastructure.mapper.BookMapper;

@Repository
public class JpaBookRepository implements BookRepository {

    private final SpringDataBookRepository repository;

    public JpaBookRepository(SpringDataBookRepository repository) {
        this.repository = repository;
    }

    /**
     * This method is used to add a book to the database.
     * 
     * @param BookModel book 
     * 
     */
    @Override
    public void createBook(BookModel book) {
        // Convert BookModel to Entity
        BookEntity bookEntity = BookMapper.modelToEntity(book);

        //save the book
        repository.save(bookEntity);
    }

    /**
     * This method is used to get a book from the database.
     * 
     * @return Optional<BookModel> book
    */
    @Override
    public Optional<BookModel> getBook() {
        return repository.findAll()
            .stream()
            .findFirst()
            .map(BookMapper::entityToModel);
    }
    
}
