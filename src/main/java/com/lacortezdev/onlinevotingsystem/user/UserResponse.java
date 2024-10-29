package com.lacortezdev.onlinevotingsystem.user;

import com.lacortezdev.onlinevotingsystem.security.UserRole;

public record UserResponse(
        Long id,
        String firstName,
        String middleName,
        String lastName,
        UserRole role
) {
}
