package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidate")
@EntityListeners(AuditingEntityListener.class)
public class Candidate {

    @EmbeddedId
    private CandidateID id;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Candidate() {
    }
}