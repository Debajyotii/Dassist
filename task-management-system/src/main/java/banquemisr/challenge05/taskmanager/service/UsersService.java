package banquemisr.challenge05.taskmanager.service;


import banquemisr.challenge05.taskmanager.dto.UserDto;
import org.springframework.security.core.Authentication;


public interface UsersService {

    UserDto createUser(UserDto user);

    UserDto retrieveCurrentAuthUser(Authentication authentication);

    void deleteAllUser();
}
