package user_api.service.userService;

import user_api.dto.UserDto;
import user_api.model.User;

import java.util.List;


public interface UserService {

    User createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto getUserById(Long id);
    List<UserDto> getAll();

    boolean deleteUserById(Long id);
}
