package clean.architecture.cleanarchitecture.application.cases.book;

import clean.architecture.cleanarchitecture.application.dto.book.CreateBookDto;
import clean.architecture.cleanarchitecture.domain.model.book.BookModel;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;
import clean.architecture.cleanarchitecture.infrastructure.mapper.BookMapper;

public class CreateBookCase {
    
    private final BookRepository repository;
    private final BookMapper bookMapper;
    
    public CreateBookCase(
        BookRepository repository,
        BookMapper bookMapper
    ) {
        this.repository = repository;
        this.bookMapper = bookMapper;
    }

    /**
     * Creates a new book in the system.
     * 
     * @param dto the data transfer object containing book details
    */
    public void createBook(CreateBookDto dto){

        //Create to bookModel object
        BookModel bookModel = bookMapper.dtoToModel(dto);

        //Save the bookModel object
        repository.createBook(bookModel);
    }
}
