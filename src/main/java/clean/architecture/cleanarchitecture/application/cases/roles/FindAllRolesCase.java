package clean.architecture.cleanarchitecture.application.cases.roles;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import clean.architecture.cleanarchitecture.application.dto.roles.PaginationRolesDto;
import clean.architecture.cleanarchitecture.application.utils.PaginationUtils;
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
     * </p>J
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

        String sortBy = paginationRolesDto != null
            ? paginationRolesDto.getSortBy() : null;    


        //Calling method with filter Data
        return PaginationUtils.generateResponse(
            "Role",
            foundRoles,
            flatten,
            limit,
            offset,
            sortOrder,
            sortBy,
            name,
            this::flattenMapper,
            this::detailedMapper
        );            
    }

    /*
     * Method to mapper data,
     * it take a RolesModel and it will mapping data.
     * 
     * @param RolesModel role - data to mapping
     * 
     * @return Map<String, Object> - Data already map
    */
    private Map<String, Object> flattenMapper(RolesModel role) {
        return Map.of(
            "id", role.getId(),
            "name", role.getName()
        );
    }

    /**
     * Method to mapper data,
     * it take a RolesModel and mapping data
     * 
     * @param RolesModel role - data to mapping 
     * 
     * @return Map<String,Object> - Data already map
    */
    private Map<String, Object> detailedMapper(RolesModel role) {
        //Creating map object
        Map<String, Object> map = new LinkedHashMap<>();

        //set data
        map.put("id", role.getId());
        map.put("name", role.getName());
        map.put("description", role.getDescription());

        //Return data
        return map;
    }

}
