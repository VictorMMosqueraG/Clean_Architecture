package clean.architecture.cleanarchitecture.application.cases.roles;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import clean.architecture.cleanarchitecture.application.dto.roles.PaginationRolesDto;
import clean.architecture.cleanarchitecture.domain.model.roles.RolesModel;
import clean.architecture.cleanarchitecture.domain.repository.roles.RolesRepository;

public class FindAllRolesCase {

    private final RolesRepository repository;

    public FindAllRolesCase(RolesRepository repository) {
        this.repository = repository;
    }

    
    /**
     * Finds all roles in the system, with support for filtering, sorting, and pagination.
     * <p>
     * This method retrieves roles based on the provided pagination and filter criteria from
     * the {@link PaginationRolesDto}. If the flatten mode is enabled, only the id and name
     * of the roles are returned. Other filters such as limit, offset, sort order, and name
     * are ignored in flatten mode.
     * </p>
     * <p>
     * If flatten mode is enabled and any other filter is provided (limit, offset, sortOrder, name),
     * an {@link IllegalArgumentException} will be thrown.
     * </p>
     *
     * @param paginationRolesDto the {@link PaginationRolesDto} containing the pagination
     *                            and filter criteria for the roles retrieval.
     * @return a list containing a single map with context information, the total data count,
     *         and the filtered, sorted, and paginated list of roles.
     * @throws IllegalArgumentException if flatten mode is combined with other filters.
    */
    public List<Map<String, Object>> findAll(PaginationRolesDto paginationRolesDto){
        // Find all roles in the system
        List<RolesModel> foundRoles = repository.findAll();

        //Destructuring variables
        boolean flatten = paginationRolesDto != null 
            && paginationRolesDto.isFlatten();

        int limit = paginationRolesDto != null && paginationRolesDto.getLimit() != 0
            ? paginationRolesDto.getLimit() : 50;
            
        int offset = paginationRolesDto != null && paginationRolesDto.getOffset() != 0
            ? paginationRolesDto.getOffset() : 0;

        String sortOrder = paginationRolesDto != null 
            ? paginationRolesDto.getSortOrder() : null;

        String name = paginationRolesDto != null 
            ? paginationRolesDto.getName() : null;

        //if flatter is provide then return with flatten format
        if (flatten) {
            //If flatten and other filter is provide it will return error
            if (limit != 50 || offset != 0 || sortOrder != null || name != null) {
                throw new IllegalArgumentException("Flatten mode can't be combined with other filters.");
            }

            //Create variable response
            Map<String, Object> response = new LinkedHashMap<>();

            response.put("Context", "Role");
            response.put("TotalData", foundRoles.size());
            response.put("Data", foundRoles.stream()
                .map(book -> {
                    Map<String, Object> bookMap = new LinkedHashMap<>();
                    bookMap.put("id", book.getId());
                    bookMap.put("title", book.getName());
                    return bookMap;
                })
                .collect(Collectors.toList())
            );

            return Collections.singletonList(response);
        }

        //Create variable with filter by name
        Predicate<RolesModel> nameFilter = role ->
        name == null || role.getName().toLowerCase().contains(name.toLowerCase());

        //Create sort by name with format ASC or DESC
        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("title"));

        if ("DESC".equalsIgnoreCase(sortOrder)) {
            sort = sort.reversed();
        }

        //Create variable response
        Map<String, Object> response = new LinkedHashMap<>();
        
        response.put("Context", "Role");
        response.put("TotalData", foundRoles.size());
        response.put("Data", foundRoles.stream()
            .filter(nameFilter)
            .map(book -> {
                Map<String, Object> bookMap = new LinkedHashMap<>();
                bookMap.put("id", book.getId());
                bookMap.put("title", book.getName());
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
