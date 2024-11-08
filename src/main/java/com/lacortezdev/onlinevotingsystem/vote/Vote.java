package com.lacortezdev.onlinevotingsystem.vote;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lacortezdev.onlinevotingsystem.ballot.Ballot;
import com.lacortezdev.onlinevotingsystem.candidate.Candidate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne()
    @JoinColumn(name = "ballot_id")
    @JsonBackReference
    private Ballot ballot;

    @OneToOne()
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;

}
