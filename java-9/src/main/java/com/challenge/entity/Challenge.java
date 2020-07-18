package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "challenge")
@EntityListeners(AuditingEntityListener.class)
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 50)
    @Column(name = "slug")
    private String slug;

    @OneToMany(mappedBy = "challenge", targetEntity = Acceleration.class)
    private List<Acceleration> acceleration;

    @OneToMany(mappedBy = "id.challenge", targetEntity = Submission.class)
    private List<Submission> submissions;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Challenge() {
    }
}