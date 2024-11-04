package com.raghoul.raghoultasks.service.authentication;

import com.raghoul.raghoultasks.dto.user.LoginUserDto;
import com.raghoul.raghoultasks.dto.user.RegisterUserDto;
import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.dto.user.VerifyUserDto;

public interface AuthenticationService {

    UserDto signUp(RegisterUserDto input);
    UserDto authenticate(LoginUserDto input);
    UserDto verifyUser(VerifyUserDto input);
    UserDto resendVerificationCode(String email);
}
