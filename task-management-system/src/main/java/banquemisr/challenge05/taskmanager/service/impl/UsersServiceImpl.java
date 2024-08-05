package banquemisr.challenge05.taskmanager.service.impl;


import banquemisr.challenge05.taskmanager.dto.UserDto;
import banquemisr.challenge05.taskmanager.exception.CustomException;
import banquemisr.challenge05.taskmanager.model.User;
import banquemisr.challenge05.taskmanager.repository.UserRepository;
import banquemisr.challenge05.taskmanager.service.UsersService;
import banquemisr.challenge05.taskmanager.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    public UserDto createUser(UserDto userDto) {
        User user = dtoMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return dtoMapper.toDTO(userRepository.save(savedUser));
    }

    public UserDto retrieveCurrentAuthUser(Authentication authentication) {
        return dtoMapper.toDTO(Optional.of(userRepository).orElseThrow(() -> new CustomException("authentication not done")).
                findByUsername(authentication.getName()).orElseThrow());
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }
}
