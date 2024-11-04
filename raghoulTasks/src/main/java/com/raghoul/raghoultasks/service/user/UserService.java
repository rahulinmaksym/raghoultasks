package com.raghoul.raghoultasks.service.user;

import com.raghoul.raghoultasks.dto.user.UserDto;

public interface UserService {

    UserDto getUserByEmail(String email);
    UserDto save(UserDto input);
}