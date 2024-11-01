package com.raghoul.raghoultasks.dto.user;

import lombok.*;

import java.security.Timestamp;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    UUID id;
    String name;
    String email;
    String password;
    Boolean enabled;
    Integer verificationCode;
    Timestamp verificationExpireDate;
}
