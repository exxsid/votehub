package com.lacortezdev.onlinevotingsystem.candidate.dto;

import lombok.Builder;

@Builder
public record CandidateResponseBody (
        Long candidateId,
        Long electionId,
        String firstName,
        String lastName,
        String party
){
}
