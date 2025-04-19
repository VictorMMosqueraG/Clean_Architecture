package clean.architecture.cleanarchitecture.domain.repository.book;

import java.util.Optional;

import clean.architecture.cleanarchitecture.domain.model.book.BookModel;

public interface BookRepository {
    
    public void createBook(BookModel book);
    public Optional<BookModel> getBook();
    public BookModel getBookByIdOrFail(Integer id);
    public void deleteBook(Integer id);
    public BookModel updateBook(Integer id, BookModel book);
}
