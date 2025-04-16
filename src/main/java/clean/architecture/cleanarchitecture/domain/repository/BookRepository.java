package clean.architecture.cleanarchitecture.domain.repository;

import java.util.Optional;

import clean.architecture.cleanarchitecture.domain.model.BookModel;

public interface BookRepository {
    
    public void createBook(BookModel book);

    public Optional<BookModel> getBook();
}
