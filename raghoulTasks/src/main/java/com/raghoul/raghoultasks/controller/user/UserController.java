package com.raghoul.raghoultasks.controller.user;

import com.raghoul.raghoultasks.dto.user.UserDto;
import com.raghoul.raghoultasks.entity.user.User;
import com.raghoul.raghoultasks.mapper.user.UserMapper;
import com.raghoul.raghoultasks.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = userMapper.userToUserDto((User) authentication.getPrincipal());
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllTasks() {
        List<UserDto> users = userService.getAll();
        if(users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(users);
    }
}
