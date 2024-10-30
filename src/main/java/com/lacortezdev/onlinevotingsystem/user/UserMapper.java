package com.lacortezdev.onlinevotingsystem.user;

import com.lacortezdev.onlinevotingsystem.security.UserRole;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponse userToUserResponse(User u) {
        return new UserResponse(
                u.getId(),
                u.getFirstName(),
                u.getMiddleName(),
                u.getLastName(),
                u.getRoles()
        );
    }

    public User userRequestBodyToUser(UserRequestBody u) {
        return User.builder()
                .id(u.id())
                .firstName(u.firstName())
                .middleName(u.middleName())
                .lastName(u.lastName())
                .isActive(u.isActive())
                .roles(UserRole.valueOf(u.role().toUpperCase()))
                .build();
    }

}
