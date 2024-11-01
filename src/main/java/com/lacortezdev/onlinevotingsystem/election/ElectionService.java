package com.lacortezdev.onlinevotingsystem.election;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;

    @Autowired
    public ElectionService(ElectionRepository electionRepository,
                           ElectionMapper electionMapper
    ) {
        this.electionRepository = electionRepository;
        this.electionMapper = electionMapper;
    }

    public Election saveElection(ElectionRequestBody requestBody) {
        Election newElection = this.electionMapper.electionRequestBodyToElection(requestBody);

        Election savedElection = this.electionRepository.save(newElection);

        return savedElection;
    }

    public List<ElectionResponseBody> getElections() {
        List<ElectionResponseBody> elections = this.electionRepository.findAll()
                .stream()
                .map(this.electionMapper::electionToElectionResponseBody)
                .toList();
        return elections;
    }

}
