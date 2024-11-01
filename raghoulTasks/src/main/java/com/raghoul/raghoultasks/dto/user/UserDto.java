package com.raghoul.raghoultasks.dto.user;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    UUID id;
    String username;
    String email;
    String password;
    Boolean enabled;
    String verificationCode;
    LocalDateTime verificationCodeExpireAt;

    UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
