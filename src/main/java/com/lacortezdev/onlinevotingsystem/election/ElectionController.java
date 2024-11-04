package com.lacortezdev.onlinevotingsystem.election;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("elections")
public class ElectionController {

    private final ElectionService electionService;
    private final ElectionMapper mapper;

    @Autowired
    public ElectionController(ElectionService electionService, ElectionMapper mapper) {
        this.electionService = electionService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<ElectionResponseBody> getElectionList() {
        return this.electionService.getElections();
    }

    @PostMapping()
    public ResponseEntity<ElectionResponseBody> createElection(@RequestBody ElectionRequestBody requestBody) {
        Election election = this.electionService.saveElection(requestBody);

        if (election == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ElectionResponseBody responseBody = this.mapper.electionToElectionResponseBody(election);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @DeleteMapping("{electionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteElection(@PathVariable Long electionId) {
        this.electionService.deleteElectionById(electionId);
    }

}
