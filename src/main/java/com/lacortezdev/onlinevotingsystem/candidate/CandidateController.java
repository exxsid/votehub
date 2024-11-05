package com.lacortezdev.onlinevotingsystem.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping()
    public ResponseEntity<CandidateDto> createNewCandidate(
            @RequestBody CandidateDto requestBody
    ) {
        try {
            CandidateDto newCandidate = this.candidateService.createCandidate(requestBody);
            return new ResponseEntity<>(newCandidate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("add-bulk")
    public ResponseEntity<List<CandidateDto>> createNewCandidate(
            @RequestBody List<CandidateDto> requestBody
    ) {
        List<CandidateDto> newCandidates = this.candidateService.createCandidateInBulk(requestBody);

        return new ResponseEntity<>(newCandidates, HttpStatus.CREATED);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateCandidate(
            @RequestBody CandidateDto dto
    ) {
        try {
            this.candidateService.updateCandidate(dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
