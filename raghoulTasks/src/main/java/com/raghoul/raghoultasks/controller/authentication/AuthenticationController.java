package com.raghoul.raghoultasks.controller.authentication;

import com.raghoul.raghoultasks.dto.user.LoginUserDto;
import com.raghoul.raghoultasks.dto.user.RegisterUserDto;
import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.dto.user.VerifyUserDto;
import com.raghoul.raghoultasks.mapper.user.UserMapper;
import com.raghoul.raghoultasks.response.LoginResponse;
import com.raghoul.raghoultasks.service.authentication.AuthenticationService;
import com.raghoul.raghoultasks.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        UserDto userDto = authenticationService.signUp(registerUserDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserDto userDto = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(userMapper.userDtoToUser(userDto));
        LoginResponse loginResponse = new LoginResponse(token, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            UserDto userDto = authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestBody String email) {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code resent");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
