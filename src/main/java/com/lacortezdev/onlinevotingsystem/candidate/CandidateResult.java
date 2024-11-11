package com.lacortezdev.onlinevotingsystem.candidate;

import lombok.Builder;

@Builder
public record CandidateResult (
        Long candidateId,
        String name,
        Long voteCount
){
}
