package com.lacortezdev.onlinevotingsystem.election;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ElectionResponseBody (
        Long id,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate
){
}
