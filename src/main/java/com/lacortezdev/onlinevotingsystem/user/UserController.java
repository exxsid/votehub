package com.lacortezdev.onlinevotingsystem.user;

import com.lacortezdev.onlinevotingsystem.security.UserRole;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserResponseMapper userResponseMapper;

    @Autowired
    public UserController(UserService userService, UserResponseMapper userResponseMapper) {
        this.userService = userService;
        this.userResponseMapper = userResponseMapper;
    }

    @GetMapping()
    public List<UserResponse> getUsers() {
        List<UserResponse> usersResponse = userService.getAllUsers()
                .stream()
                .map(this.userResponseMapper::apply)
                .toList();
        return usersResponse;
    }
}
