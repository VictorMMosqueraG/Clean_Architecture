package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import clean.architecture.cleanarchitecture.application.cases.roles.CreateRoleCase;
import clean.architecture.cleanarchitecture.application.cases.roles.DeleteRolesCase;
import clean.architecture.cleanarchitecture.application.cases.roles.FindAllRolesCase;
import clean.architecture.cleanarchitecture.application.cases.roles.FindByIdRolesCase;
import clean.architecture.cleanarchitecture.application.cases.roles.UpdateRolesCase;
import clean.architecture.cleanarchitecture.application.dto.roles.CreateRolesDto;
import clean.architecture.cleanarchitecture.application.dto.roles.PaginationRolesDto;
import clean.architecture.cleanarchitecture.application.dto.roles.UpdateRolesDto;
import clean.architecture.cleanarchitecture.infrastructure.controller.bases.BaseController;
import clean.architecture.cleanarchitecture.infrastructure.enums.ApiResponseStatus;
import clean.architecture.cleanarchitecture.infrastructure.response.ApiResponseData;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("role")
@Tag(name = "Role")
@Validated
public class RolesController implements BaseController<CreateRolesDto, Integer, UpdateRolesDto, PaginationRolesDto>{

    
    private final CreateRoleCase createRoleCase;
    private final FindByIdRolesCase findByIDRoleCase;
    private final DeleteRolesCase deleteRoleCase;
    private final UpdateRolesCase updateRolesCase;
    private final FindAllRolesCase findAllRolesCase;

    public RolesController(
        CreateRoleCase createRoleCase, 
        FindByIdRolesCase findByIDRoleCase,
        DeleteRolesCase deleteRoleCase,
        UpdateRolesCase updateRolesCase,
        FindAllRolesCase findAllRolesCase
    ) {
        this.createRoleCase = createRoleCase;
        this.findByIDRoleCase = findByIDRoleCase;
        this.deleteRoleCase = deleteRoleCase;
        this.updateRolesCase = updateRolesCase;
        this.findAllRolesCase = findAllRolesCase;
    }

    /**
     * Method to create a new role in the system.
     * It receives a CreateRolesDto object in the request body,
     * Which contains the necessary information to create a new role.
     * It calls the createRoleCase to handle the business logic of creating a role.
     * 
     * @param dto CreateRolesDto object containing the role information
     * 
     * @return ResponseEntity with a success message and HTTP status code
    */
    @Operation(
        summary = "Create a new Role",
        description = "Create a new Role."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Role created successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"message\": \"Role created successfully\", \"status\": 201}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Duplicate resource found. It may already exist.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 409, \"message\": \"Duplicate resource found. It may already exist.\", \"error\": \"Key (name)=(Role Name) already exists.\", \"path\": \"/role\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error occurred. Please check your input.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 400, \"message\": \"Validation error occurred. Please check your input.\", \"error\": \"name: Name is required, it cannot be blank or null\", \"path\": \"/role\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 500, \"message\": \"An unexpected error occurred. Please try again later.\", \"error\": \"An unexpected error occurred. Please try again later.\", \"path\": \"/role\"}")
                )
            }
        )
    })
    @PostMapping()
    public ResponseEntity<?> create(
        @Valid
        @RequestBody CreateRolesDto dto
    ) {
        // Call the use case to create a role
        createRoleCase.createRoles(dto);

        // Return and indicate success
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponseData(
                ApiResponseStatus.ROLE_CREATE_SUCCESS.getMessage(),
                ApiResponseStatus.ROLE_CREATE_SUCCESS.getStatus())
            );
    }

    /**
     * Method to handle validation errors.
     * It returns a ResponseEntity with a 400 Bad Request status and an error message.
     * 
     * @param ex the exception that was thrown
     * @return ResponseEntity with a 400 Bad Request status and an error message
    */
    @Operation(
        summary = "Find Role by ID",
        description = "Find Role by ID."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Role found successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                example = "{ \"id\": 1, \"name\": \"testRole\", \"description\": \"descriptionRole\" }"
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Role not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 404, \"message\": \"Resource not found. Please check the resource ID..\", \"error\": \"Role not found\", \"path\": \"/role/{id}\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 500, \"message\": \"An unexpected error occurred. Please try again later.\", \"error\": \"An unexpected error occurred. Please try again later.\", \"path\": \"/role\"}")
                )
            }
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(findByIDRoleCase.findById(id));
    }

    /**
     * Method to delete a role by id,
     * it take an id as a path variable and call the deleteRole from the use case.
     * it returns a ResponseEntity with a success message and HTTP status code.
     * 
     * @param id the id of the role to be deleted
     * 
     * @return ResponseEntity with a success message and HTTP status code
     * 
    */
    @Operation(
        summary = "Delete a Role by ID",
        description = "Delete a Role by ID."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Role deleted successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"message\": \"Role deleted successfully\", \"status\": 200}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Role not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 404, \"message\": \"Resource not found. Please check the resource ID..\", \"error\": \"Role not found\", \"path\": \"/role/{id}\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 500, \"message\": \"An unexpected error occurred. Please try again later.\", \"error\": \"An unexpected error occurred. Please try again later.\", \"path\": \"/Role\"}")
                )
            }
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        // Call the use case to delete a role
        deleteRoleCase.deleteRoles(id);

        // Return and indicate success
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ApiResponseData(
                ApiResponseStatus.ROLE_DELETE_SUCCESS.getMessage(),
                ApiResponseStatus.ROLE_DELETE_SUCCESS.getStatus())
            );
    }


    /**
     * Method to update a roles entity,
     * it receives a UpdateROlesDto object, in the request body,
     * and it receives an id in the path.
     * IT calling the updateROlesCase from use case.
     * 
     * @param UpdateRolesDto dto
     * @param Integer id
     * 
     * @return ResponseEntity with a success message and http status code
    */
    @Operation(
        summary = "Update a new Role",
        description = "Update a new Role."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Role update successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"message\": \"Role update successfully\", \"status\": 200}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Duplicate resource found. It may already exist.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 409, \"message\": \"Duplicate resource found. It may already exist.\", \"error\": \"Key (name)=(Role Name) already exists.\", \"path\": \"/role/{id}\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error occurred. Please check your input.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 400, \"message\": \"Validation error occurred. Please check your input.\", \"error\": \"name: Name is required, it cannot be blank or null\", \"path\": \"/role/{id}\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\"status\": 500, \"message\": \"An unexpected error occurred. Please try again later.\", \"error\": \"An unexpected error occurred. Please try again later.\", \"path\": \"/role\"}")
                )
            }
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @Valid
        @RequestBody UpdateRolesDto dto
    ) {

        //Calling updateRolesCase from use case
        updateRolesCase.updateRolesCase(id, dto);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ApiResponseData(
                ApiResponseStatus.ROLE_UPDATE_SUCCESS.getMessage(), 
                ApiResponseStatus.ROLE_UPDATE_SUCCESS.getStatus()
                )
            );
    }

    /**
     * Method to retrieve a paginated list of roles,
     * optionally filtered by parameters defined in the PaginationRolesDto.
     * 
     * @param paginationRolesDto object containing pagination and filter parameters
     * 
     * @return ResponseEntity with a list of roles and pagination metadata
    */
    @Operation(
        summary = "Find all roles with optional filters",
        description = "Retrieve a list of roles with optional filters and pagination"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Roles retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = 
                    "[{" +
                    "    \"Context\": \"Role\"," +
                    "    \"TotalData\": 2," +
                    "    \"Data\": [" +
                    "        {" +
                    "            \"id\": 1," +
                    "            \"title\": \"admin\"," +
                    "            \"description\": \"Administrator role\"" +
                    "        }," +
                    "        {" +
                    "            \"id\": 2," +
                    "            \"title\": \"user\"," +
                    "            \"description\": \"Regular user role\"" +
                    "        }" +
                    "    ]" +
                    "}]"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid filter combination",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = 
                    "{" +
                    "    \"status\": 400," +
                    "    \"message\": \"Invalid Filter combination.\"," +
                    "    \"error\": \"Flatten mode can't be combined with other filters.\"," +
                    "    \"path\": \"/role\"" +
                    "}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = 
                    "{" +
                    "    \"status\": 500," +
                    "    \"message\": \"An unexpected error occurred. Please try again later.\"," +
                    "    \"error\": \"An unexpected error occurred. Please try again later.\"," +
                    "    \"path\": \"/role\"" +
                    "}"
                )
            )
        )
    })
    @GetMapping()    
    @Override
    public ResponseEntity<?> findAll(
        @ParameterObject
        @Valid
        PaginationRolesDto paginationRolesDto
    ) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(findAllRolesCase.findAll(paginationRolesDto));
    }
    

}
