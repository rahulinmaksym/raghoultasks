package com.raghoul.raghoultasks.service.user;

import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.entity.user.User;
import com.raghoul.raghoultasks.mapper.user.UserMapper;
import com.raghoul.raghoultasks.repository.user.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserDto getUserByEmail(String email) {

        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        return userMapper.userToUserDto(user);
    }

    public UserDto save(UserDto input) {

        User user = userMapper.userDtoToUser(input);

        UUID id = null;
        boolean isUnique = false;
        while(!isUnique) {
            id = UUID.randomUUID();
            isUnique = !userRepo.existsById(id);
        }
        user.setId(id);

        return userMapper.userToUserDto(userRepo.save(user));
    }

    public List<UserDto> getAll() {
        return userMapper.userListToUserDtoList(userRepo.findAll());
    }
}
