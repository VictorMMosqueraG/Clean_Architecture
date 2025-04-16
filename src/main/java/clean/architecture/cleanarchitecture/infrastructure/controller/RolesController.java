package clean.architecture.cleanarchitecture.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clean.architecture.cleanarchitecture.application.cases.roles.CreateRoleCase;
import clean.architecture.cleanarchitecture.application.dto.roles.CreateRolesDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("role")
public class RolesController {
    
    private final CreateRoleCase createRoleCase;

    public RolesController(CreateRoleCase createRoleCase) {
        this.createRoleCase = createRoleCase;
    }

    @PostMapping()
    public ResponseEntity<?> createRoles(
        @RequestBody CreateRolesDto dto
    ) {
        createRoleCase.createRoles(dto);

        return ResponseEntity
            .ok()
            .body("Role created successfully");
    }
    
}
