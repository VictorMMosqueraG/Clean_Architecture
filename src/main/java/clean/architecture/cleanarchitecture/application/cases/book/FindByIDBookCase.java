package clean.architecture.cleanarchitecture.application.cases.book;

import clean.architecture.cleanarchitecture.domain.model.book.BookModel;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;

public class FindByIDBookCase {
    
    private final BookRepository repository;

    public FindByIDBookCase(
        BookRepository repository
    ) {
        this.repository = repository;
    }

    /**
     * Method to find by id a book,
     * it take a id and find by id, then return the book
     * 
     * @param id the id of the book to find
     * 
     * @return the book found
     */ 
    public BookModel findById(Integer id) {
        // Find the book by ID and return it
        return repository.findByIdOrFail(id);        
    }
}
