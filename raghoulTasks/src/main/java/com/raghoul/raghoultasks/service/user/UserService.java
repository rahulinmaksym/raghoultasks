package com.raghoul.raghoultasks.service.user;

import com.raghoul.raghoultasks.entity.user.User;

public interface UserService {

    User getUserByEmail(String email);
}
