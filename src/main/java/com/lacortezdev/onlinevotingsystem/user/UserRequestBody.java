package com.lacortezdev.onlinevotingsystem.user;

import jakarta.annotation.Nonnull;

public record UserRequestBody (
        Long id,
        String firstName,
        String middleName,
        String lastName,
        boolean isActive,
        String role
) {
}
