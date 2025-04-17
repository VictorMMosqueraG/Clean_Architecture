package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clean.architecture.cleanarchitecture.application.cases.roles.CreateRoleCase;
import clean.architecture.cleanarchitecture.application.dto.roles.CreateRolesDto;
import clean.architecture.cleanarchitecture.infrastructure.enums.ApiResponseStatus;
import clean.architecture.cleanarchitecture.infrastructure.response.ApiResponseData;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("role")
@Tag(name = "Role")
@Validated
public class RolesController {
    
    private final CreateRoleCase createRoleCase;

    public RolesController(CreateRoleCase createRoleCase) {
        this.createRoleCase = createRoleCase;
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
    public ResponseEntity<?> createRoles(
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
    
}
