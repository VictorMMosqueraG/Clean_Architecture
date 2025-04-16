package clean.architecture.cleanarchitecture.infrastructure.mapper;

import org.springframework.stereotype.Component;

import clean.architecture.cleanarchitecture.application.dto.book.CreateBookDto;
import clean.architecture.cleanarchitecture.domain.model.book.BookModel;
import clean.architecture.cleanarchitecture.infrastructure.entity.BookEntity;
import clean.architecture.cleanarchitecture.infrastructure.mapper.base.GenericMapper;


@Component
public class BookMapper implements GenericMapper<BookEntity, BookModel, CreateBookDto> {
    
    /**
     * Method to convert a BookModel object to a BookEntity object,
     * take the BookModel object and convert it to a BookEntity object
     * 
     * @param BookModel book - Object to be convert
     * 
     * @return BookEntity - Convert object
    */
    @Override
    public  BookEntity modelToEntity(BookModel model) {
        //Create a new BookEntity object
        BookEntity bookEntity = new BookEntity();

        //Set the properties of the BookEntity object
        bookEntity.setId(model.getId());
        bookEntity.setTittle(model.getTittle());
        bookEntity.setDescription(model.getDescription());

        return bookEntity;
    }

    /**
     * Method to convert a BookEntity object to a BookModel object,
     * take the BookEntity object and convert it to a BookModel object
     * 
     * @param BookEntity bookEntity - Object to be convert
     * 
     * @return BookModel - Convert object
    */
    @Override
    public  BookModel entityToModel(BookEntity bookEntity) {
        //Create a new BookModel object
        BookModel book = new BookModel();

        //Set the properties of the BookModel object
        book.setId(bookEntity.getId());
        book.setTittle(bookEntity.getTittle());
        book.setDescription(bookEntity.getDescription());

        return book;
    }

    /**
     * Method to convert a CreateBookDto object to a BookModel object,
     * take the CreateBookDto object and convert it to a BookModel object
     * 
     * @param CreateBookDto dto - Object to be convert
     * 
     * @return BookModel - Convert object
    */
    public BookModel dtoToModel(CreateBookDto dto){
        //Create a new BookModel object
        BookModel book = new BookModel();

        //Set the properties of the BookModel object
        book.setTittle(dto.getTittle());
        book.setDescription(dto.getDescription());

        return book;
    }
     
}
    