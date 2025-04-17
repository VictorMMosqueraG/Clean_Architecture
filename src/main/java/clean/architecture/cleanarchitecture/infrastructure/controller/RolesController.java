package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clean.architecture.cleanarchitecture.application.cases.roles.CreateRoleCase;
import clean.architecture.cleanarchitecture.application.dto.roles.CreateRolesDto;
import clean.architecture.cleanarchitecture.infrastructure.enums.ApiResponseStatus;
import clean.architecture.cleanarchitecture.infrastructure.response.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("role")
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
            .body(new ApiResponse(
                ApiResponseStatus.ROLE_CREATE_SUCCESS.getMessage(),
                ApiResponseStatus.ROLE_CREATE_SUCCESS.getStatus())
            );
    }
    
}
