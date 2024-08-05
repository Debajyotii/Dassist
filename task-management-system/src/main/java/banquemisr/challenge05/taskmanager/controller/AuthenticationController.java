package banquemisr.challenge05.taskmanager.controller;

import banquemisr.challenge05.taskmanager.dto.AuthenticateResponseDto;
import banquemisr.challenge05.taskmanager.dto.AuthenticationRequestDto;
import banquemisr.challenge05.taskmanager.dto.RefreshTokenRequestDto;
import banquemisr.challenge05.taskmanager.exception.CustomException;
import banquemisr.challenge05.taskmanager.service.AuthService;
import banquemisr.challenge05.taskmanager.service.UsersService;
import banquemisr.challenge05.taskmanager.util.JwtTokenUtil;
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

import javax.security.auth.login.FailedLoginException;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/taskmanager/auth")
@Tag(name = "Authentication", description = "Authentication and authorization operations")
public class AuthenticationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthService authService;
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Operation(
            summary = "Login",
            description = "Authenticate a user and generate access and refresh tokens",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful with tokens"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticateResponseDto> createAuthenticationToken(
            @Parameter(description = "Credentials for authentication") @Valid @RequestBody AuthenticationRequestDto authenticationRequest) throws CustomException, FailedLoginException {
        authService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final String accessToken = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
        final String refreshToken = jwtTokenUtil.generateRefreshToken(authenticationRequest.getUsername());
        return ResponseEntity.ok(new AuthenticateResponseDto(accessToken, refreshToken));
    }

    @Operation(
            summary = "Refresh Token",
            description = "Refresh the access token using a valid refresh token",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New access and refresh tokens generated"),
            @ApiResponse(responseCode = "400", description = "Invalid or expired refresh token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/token/refresh")
    public ResponseEntity<AuthenticateResponseDto> refreshToken(
            @Parameter(description = "Refresh token for generating new tokens") @Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) throws CustomException {
        // Validate refresh token and generate new tokens
        String username = jwtTokenUtil.getUsernameFromToken(refreshTokenRequestDto.getRefreshToken());
        if (username == null || jwtTokenUtil.isTokenExpired(refreshTokenRequestDto.getRefreshToken())) {
            throw new CustomException("Invalid or expired refresh token");
        }

        final String newAccessToken = jwtTokenUtil.generateToken(username);
        final String newRefreshToken = jwtTokenUtil.generateRefreshToken(username);

        return ResponseEntity.ok(new AuthenticateResponseDto(newAccessToken, newRefreshToken));
    }
}
