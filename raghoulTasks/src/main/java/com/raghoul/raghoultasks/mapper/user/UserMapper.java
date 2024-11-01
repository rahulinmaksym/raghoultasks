package com.raghoul.raghoultasks.mapper.user;

import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    UserDto userToUserDto(User entity);

    User userDtoToUser(UserDto dto);

    List<UserDto> userListToUserDtoList(List<User> entityList);

    List<User> userDtoListToUserDtoList(List<UserDto> dtoList);
}
