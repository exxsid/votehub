package com.lacortezdev.onlinevotingsystem.candidate;

import com.lacortezdev.onlinevotingsystem.election.Election;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public Candidate candidateDtoToCandidate(CandidateDto c) {
        return Candidate.builder()
                .candidateId(c.candidateId())
                .election(
                        Election.builder()
                                .electionId(c.electionId())
                                .build()
                )
                .firstName(c.firstName())
                .lastName(c.lastName())
                .party(c.party())
                .build();
    }

    public CandidateDto candidateToCandidateDto(Candidate c) {
        return CandidateDto.builder()
                .candidateId(c.getCandidateId())
                .electionId(c.getElection().getElectionId())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .party(c.getParty())
                .build();
    }

}
