package com.lacortezdev.onlinevotingsystem.candidate.dto;

public record CandidateRequestBody (
        Long electionId,
        String firstName,
        String lastName,
        String party
) {
}
