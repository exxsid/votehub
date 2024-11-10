package com.lacortezdev.onlinevotingsystem.candidate;

import lombok.Builder;

@Builder
public record CandidateResult (
        String name,
        Long voteCount
){
}
