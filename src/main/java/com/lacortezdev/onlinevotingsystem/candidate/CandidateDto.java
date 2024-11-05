package com.lacortezdev.onlinevotingsystem.candidate;

import lombok.Builder;

@Builder
public record CandidateDto (
        Long candidateId,
        Long electionId,
        String firstName,
        String lastName,
        String party
){
}
