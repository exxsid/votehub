package com.lacortezdev.onlinevotingsystem.candidate.dto;

import com.lacortezdev.onlinevotingsystem.candidate.Candidate;
import com.lacortezdev.onlinevotingsystem.election.Election;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public Candidate candidateRequestBodyToCandidate(CandidateRequestBody requestBody) {
        return Candidate.builder()
                .election(
                        Election.builder()
                                .electionId(requestBody.electionId())
                                .build()
                )
                .firstName(requestBody.firstName())
                .lastName(requestBody.lastName())
                .party(requestBody.party())
                .build();
    }

    public CandidateResponseBody candidateToCandidateResponseBody(Candidate c) {
        return CandidateResponseBody.builder()
                .candidateId(c.getCandidateId())
                .electionId(c.getElection().getElectionId())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .party(c.getParty())
                .build();
    }

}
