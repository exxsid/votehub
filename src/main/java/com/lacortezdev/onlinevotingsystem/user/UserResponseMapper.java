package com.lacortezdev.onlinevotingsystem.user;

import org.springframework.stereotype.Service;

@Service
public class UserResponseMapper {

    public UserResponse apply(User u) {
        return new UserResponse(
                u.getId(),
                u.getFirstName(),
                u.getMiddleName(),
                u.getLastName(),
                u.getRoles()
        );
    }

}
