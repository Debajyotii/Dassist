package banquemisr.challenge05.taskmanager.controller;

import banquemisr.challenge05.taskmanager.dto.UserDto;
import banquemisr.challenge05.taskmanager.exception.CustomException;
import banquemisr.challenge05.taskmanager.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/taskmanager/user")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Operation(
            summary = "Register a new user",
            description = "Create a new user with the provided details",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/register")
    public ResponseEntity<String> createUser(
            @Parameter(description = "User details for registration") @Valid @RequestBody UserDto userDto) throws CustomException {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return ResponseEntity.ok("User registered successfully: " + usersService.createUser(userDto).getUsername());
    }
}
