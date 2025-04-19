package clean.architecture.cleanarchitecture.application.cases.book;


import clean.architecture.cleanarchitecture.application.dto.book.UpdateBookDto;
import clean.architecture.cleanarchitecture.domain.model.book.BookModel;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;
import clean.architecture.cleanarchitecture.infrastructure.mapper.BookMapper;

public class UpdateBookCase {
    
    private final BookRepository repository;
    private final BookMapper bookMapper;

    public UpdateBookCase(
        BookRepository repository,
        BookMapper bookMapper
    ) {
        this.repository = repository;
        this.bookMapper = bookMapper;
    }


    /**
     * Method to update book,
     * it take a DTO and will convert to model,
     * then calling a updateFiles method from model,
     * then it calling to updateBook method from repository
     * 
     * @param Integer id
     * @param UpdateBookDto 
     * 
     * @return BookModel
     *
    */
    public BookModel updateBookCase(
        Integer id, 
        UpdateBookDto updateBookDto
    ){
               
        // Convert Dto to model
        BookModel bookModel = bookMapper.dtoUpdateToModel(updateBookDto);

        //Valid if the file provide if null or not
        bookModel.updateFields(updateBookDto);

        //calling the method to update from repository and return the result
        return repository.update(id, bookModel);
    }
}
