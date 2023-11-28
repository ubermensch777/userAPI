package user_api.service.userService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user_api.dto.UserDto;
import user_api.mapper.UserMapper;
import user_api.model.User;
import user_api.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final CurrentUserService currentUserService;
    @Override
    @Transactional
    public User createUser(UserDto userDto){
        if (currentUserService.getCurrentUser() == null
                &&
                !userRepository.existsByUsername(userDto.getUsername())
        ) {
            User user = userMapper.toEntity(userDto);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setIsActive(true);
            return userRepository.save(user);
        } return null;
    }
    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto){
        User oldUser = userRepository.findById(userDto.getId()).orElse(null);
        if (oldUser != null && currentUserService.checkAccess(userDto.getId())){
            if (userDto.getPassword() != null){
                oldUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            if (userDto.getAge() != null){
                oldUser.setAge(userDto.getAge());
            }
            if (userDto.getUsername() != null){
                oldUser.setUsername(userDto.getUsername());
            }
            return userMapper.toDto(userRepository.save(oldUser));
        } return null;
    }
    @Override
    public UserDto getUserById(Long id){
        return userMapper.toDto(userRepository.getUserById(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    @Transactional
    public boolean deleteUserById(Long id){
        if (currentUserService.checkAccess(id)){
            User user = userRepository.findById(id).orElse(null);
            if (user != null){
                user.setIsActive(false);
                userRepository.save(user);
            return true;
            }
        }
        return false;
    }

}