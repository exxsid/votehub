package com.lacortezdev.onlinevotingsystem.election;

import java.time.LocalDateTime;

public record ElectionRequestBody(
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
