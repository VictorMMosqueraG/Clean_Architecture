package clean.architecture.cleanarchitecture.infrastructure.mapper;

import clean.architecture.cleanarchitecture.application.dto.CreateBookDto;
import clean.architecture.cleanarchitecture.domain.model.BookModel;
import clean.architecture.cleanarchitecture.infrastructure.entity.BookEntity;

public class BookMapper {
    
    //COMEBACK: Missing documentation
    public static BookEntity modelToEntity(BookModel book) {
        //Create a new BookEntity object
        BookEntity bookEntity = new BookEntity();

        //Set the properties of the BookEntity object
        bookEntity.setId(book.getId());
        bookEntity.setTittle(book.getTittle());
        bookEntity.setDescription(book.getDescription());

        return bookEntity;
    }

    //COMEBACK: Missing documentation
    public static BookModel entityToModel(BookEntity bookEntity) {
        //Create a new BookModel object
        BookModel book = new BookModel();

        //Set the properties of the BookModel object
        book.setId(bookEntity.getId());
        book.setTittle(bookEntity.getTittle());
        book.setDescription(bookEntity.getDescription());

        return book;
    }

    //COMEBACK: Missing documentation
    public static BookModel dtoToModel(CreateBookDto dto){
        //Create a new BookModel object
        BookModel book = new BookModel();

        //Set the properties of the BookModel object
        book.setTittle(dto.getTittle());
        book.setDescription(dto.getDescription());

        return book;
    }
     
}
