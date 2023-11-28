package user_api.service.authService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import user_api.dto.UserDto;
import user_api.model.User;
import user_api.repository.UserRepository;
import user_api.service.userService.UserService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String register(UserDto userDto){
        User createdUser = userService.createUser(userDto);
        if (createdUser != null) {
            return  jwtService.generateToken(createdUser);
        } return null;
    }

    public String authenticate (UserDto userDto) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
