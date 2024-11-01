package com.lacortezdev.onlinevotingsystem.election;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ElectionMapper {

    public Election electionRequestBodyToElection(ElectionRequestBody e) {
        LocalDateTime now = LocalDateTime.now();
        return Election.builder()
                .title(e.title())
                .startDate(e.startDate())
                .endDate(e.endDate())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public ElectionResponseBody electionToElectionResponseBody (Election e) {
        return ElectionResponseBody.builder()
                .id(e.getElectionId())
                .title(e.getTitle())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .build();

    }

}
