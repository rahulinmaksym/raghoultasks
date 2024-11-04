package com.raghoul.raghoultasks.mapper.user;

import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<User> userDtoListToUserList(List<UserDto> userDtoList);

    List<UserDto> userListToUserDtoList(List<User> userList);
}
