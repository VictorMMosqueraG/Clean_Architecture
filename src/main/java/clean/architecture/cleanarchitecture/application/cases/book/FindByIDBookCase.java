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

    // COMEBACK: Missing Documentation
    public BookModel findById(Integer id) {
        // Find the book by ID and return it
        return repository.getBookById(id);        
    }
}
