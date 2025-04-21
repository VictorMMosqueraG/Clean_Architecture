package clean.architecture.cleanarchitecture.application.cases.book;

import java.util.List;

import clean.architecture.cleanarchitecture.domain.model.book.BookModel;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;

public class FindAllBookCase {
    
    private final BookRepository repository;

    public FindAllBookCase(
        BookRepository repository
    ){
        this.repository = repository;
    }

    // COMEBACK: Missing documentation
    public List<BookModel> findAll(){
        return repository.findAll();
    }
}
