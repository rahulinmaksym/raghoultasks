package com.raghoul.raghoultasks.dto.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginUserDto {

    private String email;
    private String password;
}
