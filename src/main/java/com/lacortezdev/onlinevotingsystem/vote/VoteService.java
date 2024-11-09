package com.lacortezdev.onlinevotingsystem.vote;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Transactional
    public List<Vote> saveVotes(List<Vote> votes) {
        List<Vote> newVotes = this.voteRepository.saveAll(votes);

        return newVotes;
    }
}
