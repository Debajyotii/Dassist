package banquemisr.challenge05.taskmanager.service.impl;

import banquemisr.challenge05.taskmanager.model.User;
import banquemisr.challenge05.taskmanager.repository.UserRepository;
import banquemisr.challenge05.taskmanager.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    //@Autowired Since 4.3 Explicitly Auto wiring not required for CI
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Optional.of(new SimpleGrantedAuthority(user.getRole())).stream().toList());
    }
}
