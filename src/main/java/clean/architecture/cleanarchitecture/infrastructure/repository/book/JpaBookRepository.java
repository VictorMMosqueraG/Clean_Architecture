package clean.architecture.cleanarchitecture.infrastructure.repository.book;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import clean.architecture.cleanarchitecture.domain.model.book.BookModel;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;
import clean.architecture.cleanarchitecture.infrastructure.entity.BookEntity;
import clean.architecture.cleanarchitecture.infrastructure.mapper.BookMapper;

@Repository
public class JpaBookRepository implements BookRepository {

    private final SpringDataBookRepository repository;
    private final BookMapper bookMapper;

    public JpaBookRepository(
        SpringDataBookRepository repository,
        BookMapper bookMapper
    ) {
        this.repository = repository;
        this.bookMapper = bookMapper;
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
        BookEntity bookEntity = bookMapper.modelToEntity(book);

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
            .map(bookMapper::entityToModel);
    }


    @Override
     public BookModel getBookById(Integer id) {
        // Find the book by ID, if not found, throw an exception
        BookEntity bookEntity = repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Book not found")); 

        //Convert BookEntity to BookModel and return it
        return bookMapper.entityToModel(bookEntity);
    }
    
}
