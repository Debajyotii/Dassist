package banquemisr.challenge05.taskmanager.service.impl;

import banquemisr.challenge05.taskmanager.exception.CustomException;
import banquemisr.challenge05.taskmanager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(String username, String password) throws CustomException, FailedLoginException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, new String(Base64.getDecoder().decode(password))));
        } catch (DisabledException e) {
            throw new CustomException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new FailedLoginException("INVALID_CREDENTIALS");
        }
    }
}
