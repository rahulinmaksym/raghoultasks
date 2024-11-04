package com.raghoul.raghoultasks.service.authentication;

import com.raghoul.raghoultasks.dto.user.LoginUserDto;
import com.raghoul.raghoultasks.dto.user.RegisterUserDto;
import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.dto.user.VerifyUserDto;
import com.raghoul.raghoultasks.mapper.user.UserMapper;
import com.raghoul.raghoultasks.service.email.EmailService;
import com.raghoul.raghoultasks.service.user.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public UserDto signUp(RegisterUserDto input) {

        UserDto userDto = new UserDto();
        userDto.setUsername(input.getUsername());
        userDto.setEmail(input.getEmail());
        userDto.setPassword(passwordEncoder.encode(input.getPassword()));
        userDto.setVerificationCode(generateVerificationCode());
        userDto.setVerificationCodeExpireAt(LocalDateTime.now().plusMinutes(15));
        userDto.setEnabled(false);

        sendVerificationEmail(userDto);

        return userService.save(userDto);
    }

    public UserDto authenticate(LoginUserDto input) {

        UserDto userDto = userService.getUserByEmail(input.getEmail());

        if(!userMapper.userDtoToUser(userDto).isEnabled()) {
            throw new RuntimeException("Account is not verified. Please verify your account");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        return userDto;
    }

    public UserDto verifyUser(VerifyUserDto input) {

        UserDto userDto = userService.getUserByEmail(input.getEmail());

        if(userDto.getVerificationCodeExpireAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Verification code expired. Please try again");
        }
        if(userDto.getVerificationCode().equals(input.getVerificationCode())) {
            userDto.setEnabled(true);
            userDto.setVerificationCode(null);
            userDto.setVerificationCodeExpireAt(null);
            return userService.save(userDto);
        }
        else {
            throw new RuntimeException("Invalid verification code");
        }
    }

    public UserDto resendVerificationCode(String email) {

        UserDto userDto = userService.getUserByEmail(email);

        if(userMapper.userDtoToUser(userDto).isEnabled()) {
            throw new RuntimeException("Account is already verified");
        }

        userDto.setVerificationCode(generateVerificationCode());
        userDto.setVerificationCodeExpireAt(LocalDateTime.now().plusMinutes(15));

        sendVerificationEmail(userDto);

        return userService.save(userDto);
    }

    private void sendVerificationEmail(UserDto userDto) {

        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + userDto.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendVerificationEmail(userDto.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
