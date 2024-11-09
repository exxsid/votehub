package com.lacortezdev.onlinevotingsystem.ballot;

import com.lacortezdev.onlinevotingsystem.candidate.Candidate;
import com.lacortezdev.onlinevotingsystem.election.Election;
import com.lacortezdev.onlinevotingsystem.user.User;
import com.lacortezdev.onlinevotingsystem.vote.Vote;
import com.lacortezdev.onlinevotingsystem.vote.VoteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BallotService {

    private final BallotRepository ballotRepository;
    private final VoteService voteService;

    @Autowired
    public BallotService(BallotRepository ballotRepository, VoteService voteService) {
        this.ballotRepository = ballotRepository;
        this.voteService = voteService;
    }

    @Transactional
    public void saveBallot(BallotDto ballotDto) throws Exception {
        try {
            Ballot ballot = Ballot.builder()
                    .user(
                            User.builder()
                                    .id(ballotDto.userId())
                                    .build()
                    )
                    .election(
                            Election.builder()
                                    .electionId(ballotDto.electionId())
                                    .build()
                    )
                    .castAt(LocalDateTime.now())
                    .build();
            Ballot newBallot = this.ballotRepository.save(ballot);

            List<Vote> votes = ballotDto.voteCandidateId().stream()
                    .map(candidateId -> Vote.builder()
                            .ballot(ballot)
                            .candidate(
                                    Candidate.builder()
                                            .candidateId(candidateId)
                                            .build()
                            )
                            .build()
                    )
                    .toList();

            List<Vote> newVotes = this.voteService.saveVotes(votes);
        } catch (Exception e) {
            throw new Exception();
        }

    }
}
