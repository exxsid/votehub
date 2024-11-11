package com.lacortezdev.onlinevotingsystem.election;

import com.lacortezdev.onlinevotingsystem.candidate.CandidateResult;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ElectionResult (
        Long electionId,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        List<CandidateResult> candidateResults
){

}
