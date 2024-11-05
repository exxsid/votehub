package com.lacortezdev.onlinevotingsystem.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper mapper;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, CandidateMapper mapper) {
        this.candidateRepository = candidateRepository;
        this.mapper = mapper;
    }

    public CandidateDto createCandidate(CandidateDto requestBody) throws Exception {
        Candidate candidate = this.mapper.candidateDtoToCandidate(requestBody);

        Candidate newCandidate = this.candidateRepository.save(candidate);

        if (candidate == null) {
            throw new Exception();
        }

        return this.mapper.candidateToCandidateDto(newCandidate);
    }

    public List<CandidateDto> createCandidateInBulk(List<CandidateDto> requestBody) {
        List<Candidate> candidates = requestBody.stream()
                .map(this.mapper::candidateDtoToCandidate)
                .toList();

        List<CandidateDto> newCandidates = this.candidateRepository.saveAll(candidates).stream()
                .map(this.mapper::candidateToCandidateDto)
                .toList();

        return newCandidates;

    }

    public void updateCandidate(CandidateDto dto) {
        Candidate candidate = this.mapper.candidateDtoToCandidate(dto);

        this.candidateRepository.save(candidate);
    }
}
