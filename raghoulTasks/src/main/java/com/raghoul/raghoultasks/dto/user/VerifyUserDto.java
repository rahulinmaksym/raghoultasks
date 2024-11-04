package com.raghoul.raghoultasks.dto.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VerifyUserDto {
    private String email;
    private String verificationCode;
}
