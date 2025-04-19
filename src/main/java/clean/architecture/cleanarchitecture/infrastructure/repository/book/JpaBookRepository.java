package clean.architecture.cleanarchitecture.infrastructure.repository.book;

import java.util.NoSuchElementException;

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
    public void save(BookModel book) {
        // Convert BookModel to Entity
        BookEntity bookEntity = bookMapper.modelToEntity(book);

        //save the book
        repository.save(bookEntity);
    }

    /**
     * This method is used to get book by id from the database,
     * it take an id and find book in the system,
     * if not found, it will throw an exception
     * if found, it will return the book. 
     * 
     * @param Integer id
     * 
     * @return BookModel book
     */ 
    @Override
     public BookModel findByIdOrFail(Integer id) {
        // Find the book by ID, if not found, throw an exception
        BookEntity bookEntity = repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Book not found")); 

        //Convert BookEntity to BookModel and return it
        return bookMapper.entityToModel(bookEntity);
    }

    /**
     * This method is used to delete book in the database,
     * it take an id and find book in the system,
     * if not found, it will throw exception,
     * if found, it will delete the book.
     * 
     * @param Integer id
     * 
     * @return void
    */
    @Override
    public void delete(Integer id) {
        //Valid if the book exists
        this.findByIdOrFail(id);

        //Delete the book
        repository.deleteById(id);
    }

    /**
     * Method is used to update book in the database,
     * it take an id and find book in the system,
     * if not found, it will throw exception,
     * if found, it will convert model to entity,
     * and save the book, and convert to entity to model.
     * 
     * @param Integer id
     * @param BookModel book
     * 
     * @return BookModel book
     * 
    */
    @Override
    public BookModel update(Integer id, BookModel book) {

        //Find the book by id
        BookModel foundEntity = this.findByIdOrFail(id);

        //Set values
        foundEntity.setTittle(book.getTittle());
        foundEntity.setDescription(book.getDescription());

         //convert BookModel to BookEntity
         BookEntity bookEntity = bookMapper.modelToEntity(foundEntity);


        //Save the book and convert entity object to model
        repository.save(bookEntity);

        //convert entity to model and it will returns
        return bookMapper.entityToModel(bookEntity);
    }
    
}
