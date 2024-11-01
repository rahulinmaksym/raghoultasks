package com.raghoul.raghoultasks.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
@Document
public class User {

    @Id
    UUID id;

    String name;

    String email;

    String password;

    Boolean enabled;

    Integer verificationCode;

    Timestamp verificationExpireDate;
}
