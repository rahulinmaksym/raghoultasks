package com.raghoul.raghoultasks.mapper.user;

import com.raghoul.raghoultasks.dto.user.LoginUserDto;
import com.raghoul.raghoultasks.dto.user.RegisterUserDto;
import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.dto.user.VerifyUserDto;
import com.raghoul.raghoultasks.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    LoginUserDto userToLoginUserDto(User user);
    RegisterUserDto userToRegisterUserDto(User user);
    VerifyUserDto userToVerifyUserDto(User user);
    UserDto userToUserDto(User user);

    User loginUserDtoToUser(LoginUserDto loginUserDto);
    User registerUserDtoToUser(RegisterUserDto registerUserDto);
    User verifyUserDtoToUser(VerifyUserDto verifyUserDto);
    User userDtoToUser(UserDto userDto);

    List<User> loginUserDtoListToUserList(List<LoginUserDto> loginUserDtoList);
    List<User> registerUserDtoListToUserList(List<RegisterUserDto> registerUserDtoList);
    List<User> verifyUserDtoListToUserList(List<RegisterUserDto> registerUserDtoList);
    List<User> userDtoListToUserList(List<UserDto> userDtoList);

    List<LoginUserDto> userListToLoginUserDtoList(List<User> userList);
    List<RegisterUserDto> userListToRegisterUserDtoList(List<User> userList);
    List<VerifyUserDto> userListToVerifyUserDtoList(List<User> userList);
    List<UserDto> userListToUserDtoList(List<User> userList);
}
