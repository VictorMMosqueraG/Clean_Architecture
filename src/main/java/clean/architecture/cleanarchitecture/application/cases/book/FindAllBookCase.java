package clean.architecture.cleanarchitecture.application.cases.book;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import clean.architecture.cleanarchitecture.application.dto.book.PaginationBookDto;
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


        //if flatter is provide then return with flatten format
        if (flatten) {
            //If flatten and other filter is provide it will return error
            if (limit != 50 || offset != 0 || sortOrder != null || tittle != null) {
                throw new IllegalArgumentException("Flatten mode can't be combined with other filters.");
            }

            //Create variable response
            Map<String, Object> response = new LinkedHashMap<>();

            response.put("Context", "Book");
            response.put("TotalData", foundBooks.size());
            response.put("Data", foundBooks.stream()
                .map(book -> {
                    Map<String, Object> bookMap = new LinkedHashMap<>();
                    bookMap.put("id", book.getId());
                    bookMap.put("title", book.getTittle());
                    return bookMap;
                })
                .collect(Collectors.toList())
            );

            return Collections.singletonList(response);
        }

        //Create variable with filter by tittle
        Predicate<BookModel> tittleFilter = book ->
        tittle == null || book.getTittle().toLowerCase().contains(tittle.toLowerCase());

        //Create sort by tittle with format ASC or DESC
        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("title"));

        if ("DESC".equalsIgnoreCase(sortOrder)) {
            sort = sort.reversed();
        }

        //Create variable response
        Map<String, Object> response = new LinkedHashMap<>();
        
        response.put("Context", "Book");
        response.put("TotalData", foundBooks.size());
        response.put("Data", foundBooks.stream()
            .filter(tittleFilter)
            .map(book -> {
                Map<String, Object> bookMap = new LinkedHashMap<>();
                bookMap.put("id", book.getId());
                bookMap.put("title", book.getTittle());
                bookMap.put("description", book.getDescription());
                return bookMap;
            })
            .skip(offset)
            .limit(limit)
            .sorted(sort)
            .collect(Collectors.toList())
        );

        return Collections.singletonList(response);

    }
}
