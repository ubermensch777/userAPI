package user_api.mapper;

import org.mapstruct.Mapper;
import user_api.dto.UserDto;
import user_api.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity (UserDto userDto);
    UserDto toDto (User user);
}
