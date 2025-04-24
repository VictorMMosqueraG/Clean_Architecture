package clean.architecture.cleanarchitecture.application.cases.book;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import clean.architecture.cleanarchitecture.application.dto.book.PaginationBookDto;
import clean.architecture.cleanarchitecture.application.utils.PaginationUtils;
import clean.architecture.cleanarchitecture.domain.model.book.BookModel;
import clean.architecture.cleanarchitecture.domain.repository.book.BookRepository;

public class FindAllBookCase {
    
    private final BookRepository repository;

    public FindAllBookCase(
        BookRepository repository
    ){
        this.repository = repository;
    }

    /**
     * Retrieves a list of books from the system.
     * <p>
     * Supports filtering by title, sorting by title (ascending/descending),
     * pagination using limit and offset, and an optional flatten mode.
     * </p>
     * <p>
     * If {@code flatten} is enabled, all other filters (limit, offset, sortOrder, title)
     * will be ignored and a simplified response is returned. If combined with any
     * other filter, an {@link IllegalArgumentException} will be thrown.
     * </p>
     *
     * @param paginationBookDto DTO object containing pagination and filter options.
     * @return A list containing a single map with context information, total count,
     *         and the data itself.
     * @throws IllegalArgumentException if flatten mode is combined with other filters.
    */
    public List<Map<String,Object>> findAll(PaginationBookDto paginationBookDto){
        //Find all books in the system
        List<BookModel> foundBooks = repository.findAll();

        //Destructuring variables
        boolean flatten = paginationBookDto != null 
            && paginationBookDto.isFlatten();

        int limit = paginationBookDto != null && paginationBookDto.getLimit() != 0
            ? paginationBookDto.getLimit() : 50;
            
        int offset = paginationBookDto != null && paginationBookDto.getOffset() != 0
            ? paginationBookDto.getOffset() : 0;

        String sortOrder = paginationBookDto != null 
            ? paginationBookDto.getSortOrder() : null;

        String tittle = paginationBookDto != null 
            ? paginationBookDto.getTittle() : null;

        String sortBy = paginationBookDto != null   
            ? paginationBookDto.getSortBy() : null;

        //calling method with filter data
        return PaginationUtils.generateResponse(
            "Book",
            foundBooks,
            flatten, 
            limit, 
            offset, 
            sortOrder, 
            sortBy, 
            tittle, 
            this::flattenMapper,
            this::detailedMapper
        );
    }


    /*
     * Method to mapper data,
     * it take a BookMode and it will mapping data.
     * 
     * @param BookModel book - data to mapping
     * 
     * @return Map<String, Object> - Data already map
    */
    private Map<String,Object> flattenMapper(BookModel book){
        return Map.of(
            "id", book.getId(),
            "tittle", book.getTittle()
        );
    }


    /**
     * Method to mapper data,
     * it take a BookModel and mapping data
     * 
     * @param BookModel role - data to mapping 
     * 
     * @return Map<String,Object> - Data already map
    */
    private Map<String, Object> detailedMapper(BookModel book){
        //Creating map object
        Map<String,Object> map = new LinkedHashMap<>();

        //Set value
        map.put("id", book.getId());
        map.put("tittle", book.getTittle());
        map.put("description", book.getDescription());

        //Return data
        return map;
    }


}
