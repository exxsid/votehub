package com.lacortezdev.onlinevotingsystem.candidate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lacortezdev.onlinevotingsystem.election.Election;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="candidates")
public class Candidate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId;
    private String firstName;
    private String lastName;
    private String party;

    @ManyToOne()
    @JoinColumn(name = "election_id")
    @JsonBackReference
    private Election election;

}
