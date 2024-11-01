package com.raghoul.raghoultasks.service.user;

import com.raghoul.raghoultasks.entity.user.User;
import com.raghoul.raghoultasks.repository.user.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public User getUserByEmail(String email) {

        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
