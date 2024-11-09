package com.lacortezdev.onlinevotingsystem.ballot;

import lombok.Builder;

import java.util.List;

@Builder
public record BallotDto(
        Long userId,
        Long electionId,
        List<Long> voteCandidateId
) {
}
