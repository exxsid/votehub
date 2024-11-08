package com.lacortezdev.onlinevotingsystem.ballot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lacortezdev.onlinevotingsystem.election.Election;
import com.lacortezdev.onlinevotingsystem.user.User;
import com.lacortezdev.onlinevotingsystem.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ballots")
public class Ballot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ballotId;

    @ManyToOne()
    @JoinColumn(name = "election_id")
    @JsonBackReference
    private Election election;

    @OneToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private LocalDateTime castAt;

    @OneToMany(mappedBy = "ballot")
    @JsonManagedReference
    private List<Vote> votes;

}
