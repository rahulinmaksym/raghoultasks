package com.raghoul.raghoultasks.dto.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterUserDto {
    private String email;
    private String password;
    private String username;
}
