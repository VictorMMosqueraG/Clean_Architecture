package clean.architecture.cleanarchitecture.application.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PaginationUtils {
    
    /**
     * Generates a paginated response with optional flatten format.
     *
     * @param context         the name of the resource being returned (e.g., "Role")
     * @param items           the list of items to be returned
     * @param flatten         whether to return data in flatten format
     * @param limit           maximum number of items to return
     * @param offset          number of items to skip
     * @param sortOrder       optional sort order (not used in logic here)
     * @param flattenMapper   function to map an item to a flattened representation
     * @param detailedMapper  function to map an item to a detailed representation
     * @param <T>             the type of the items
     * @return a list with a single map containing Context, TotalData, and Data keys
    */
    public static <T> List<Map<String, Object>> generateResponse(
        String context,
        List<T> items,
        boolean flatten,
        int limit,
        int offset,
        String sortOrder,
        String sortField,
        String filterValue,
        Function<T, Map<String, Object>> flattenMapper,//data with flatten format
        Function<T, Map<String, Object>> detailedMapper//Data with normal format
    ) {
       
        // Validate if flatten mode is being used with other filters
        if (
            flatten && 
            (
                limit != 50 || 
                offset != 0 || 
                filterValue != null ||
                sortField != null ||
                sortOrder != null
            )
        ) {
            throw new IllegalArgumentException("Flatten mode can't be combined with other filters.");
        }

        //Creating variable to filter
        Predicate<T> filter = 
        createFilterPredicate(
            Optional.ofNullable(filterValue)
        );

        // Create a Comparator for sorting by "title" field, handling sort order
        Comparator<Map<String, Object>> sort = 
        createSortComparator(
            Optional.ofNullable(sortField),
            Optional.ofNullable(sortOrder)
        );


        // Populate "Data" depending on flatten mode
        Map<String, Object> response = new LinkedHashMap<>();

            response.put("Context", context);
            response.put("TotalData", items.size());
            response.put("Data", items.stream()
                .filter(filter)
                .map(flatten ? flattenMapper : detailedMapper)
                .sorted(sort)
                .skip(flatten ? 0 : offset)
                .limit(flatten ? items.size() : limit)
                .collect(Collectors.toList())
            );
           
        //return data with filter    
        return Collections.singletonList(response);
    }

    
    /**
     * Creates a filter predicate based on filterValue.
     *
     * @param filterValue the value to filter by
     * @param <T>         the type of the items
     * @return a predicate for filtering items
     */
    private static <T> Predicate<T> createFilterPredicate(Optional<String> filterValue) {
        return filterValue.map(value -> (Predicate<T>) item -> {
            try {
                // Use reflection to get all String fields and check if the filter value exists in any of them
                for (var field : item.getClass().getDeclaredFields()) {
                    if (field.getType().equals(String.class)) {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(item);
                        if (fieldValue != null && fieldValue.toLowerCase().contains(value.toLowerCase())) {
                            return true; // If any field matches the filter value
                        }
                    }
                }
                return false; // No field matched the filter value
            } catch (Exception e) {
                return false; // If there's an exception, return false
            }
        }).orElse(item -> true);  // If no filter value, apply no filtering
    }

    /**
     * Creates a comparator for sorting by the specified field.
     *
     * @param sortField the field by which to sort
     * @param sortOrder the order in which to sort (ASC or DESC)
     * @return the comparator for sorting
     */
    private static Comparator<Map<String, Object>> createSortComparator(
        Optional<String> sortField, 
        Optional<String> sortOrder
    ) {
        Comparator<Map<String, Object>> sort = sortField
            .map(field ->
                Comparator.comparing(
                    (Map<String, Object> m) -> (Comparable) m.get(field),
                    Comparator.nullsLast(Comparator.naturalOrder())
                )
            )
            .orElse((m1, m2) -> 0); // No sorting if sortField is not present
    
        if ("DESC".equalsIgnoreCase(sortOrder.orElse(""))) {
            sort = sort.reversed();
        }
        return sort;
    }
    
}
