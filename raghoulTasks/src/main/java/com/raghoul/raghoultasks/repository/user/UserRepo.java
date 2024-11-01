package com.raghoul.raghoultasks.repository.user;

import com.raghoul.raghoultasks.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends MongoRepository<User, UUID> {

    User findByEmail(String email);

    User findByVerificationCode(String verificationCode);
}
