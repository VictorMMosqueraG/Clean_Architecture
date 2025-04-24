package clean.architecture.cleanarchitecture.application.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public abstract class BasePagination {
    
    @Schema(
        description = "Indicates whether the response should be flattened", 
        example = "true"
    )
    private boolean flatten;

    @Schema(
        description = "Maximum number of results to return", 
        example = "50"
    )
    @Max(
        value = 50, 
        message = "Limit cannot exceed 50"
    )
    private int limit;

    @Schema(
        description = "Number of records to skip for pagination", 
        example = "0"
    )
    @Min(
        value = 0, 
        message = "Offset cannot be negative"
    )
    private int offset;

    @Schema(
        description = "Sorting order: ASC for ascending or DESC for descending", 
        example = "ASC"
    )
    private String sortOrder;

    @Schema(
        description = "Order by value To ASC or DESC", 
        example = "tittle"
    )
    @Size(
        max = 255, 
        message = "sortBy cannot exceed 100 characters"
    )
    private String sortBy;

    // Getters y setters
    public boolean isFlatten() { return flatten; }
    public void setFlatten(boolean flatten) { this.flatten = flatten; }

    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }

    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }
    
    public String getSortOrder() { return sortOrder; }
    public void setSortOrder(String sortOrder) { this.sortOrder = sortOrder; }
    
    public String getSortBy() {return sortBy;}
    public void setSortBy(String sortBy) {this.sortBy = sortBy;}

    
}
