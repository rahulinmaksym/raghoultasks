package com.raghoul.raghoultasks.service.user;

import com.raghoul.raghoultasks.dto.user.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserByEmail(String email);
    UserDto save(UserDto input);
    List<UserDto> getAll();
}