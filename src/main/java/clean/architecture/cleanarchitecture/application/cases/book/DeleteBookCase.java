package clean.architecture.cleanarchitecture.application.cases.book;

import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;

public class DeleteBookCase {
    
    private final BookRepository bookRepository;

    public DeleteBookCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public void deleteBookCase(Integer id){
        bookRepository.deleteBook(id);//Call method from repository
    }
    
}
