package com.lacortezdev.onlinevotingsystem.candidate;

import com.lacortezdev.onlinevotingsystem.candidate.dto.CandidateMapper;
import com.lacortezdev.onlinevotingsystem.candidate.dto.CandidateRequestBody;
import com.lacortezdev.onlinevotingsystem.candidate.dto.CandidateResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    public CandidateResponseBody createCandidate(CandidateRequestBody requestBody) throws Exception {
        Candidate candidate = this.mapper.candidateRequestBodyToCandidate(requestBody);

        Candidate newCandidate = this.candidateRepository.save(candidate);

        if (candidate == null) {
            throw new Exception();
        }

        return this.mapper.candidateToCandidateResponseBody(newCandidate);
    }

    public List<CandidateResponseBody> createCandidateInBulk(List<CandidateRequestBody> requestBody) {
        List<Candidate> candidates = requestBody.stream()
                .map(this.mapper::candidateRequestBodyToCandidate)
                .toList();

        List<CandidateResponseBody> newCandidates = this.candidateRepository.saveAll(candidates).stream()
                .map(this.mapper::candidateToCandidateResponseBody)
                .toList();

        return newCandidates;

    }
}
