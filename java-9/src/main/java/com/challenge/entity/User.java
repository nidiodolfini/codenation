package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "id.user", targetEntity = Candidate.class)
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "id.user", targetEntity = Submission.class)
    private List<Submission> submissions;

    @NotNull
    @Size(max = 100)
    @Column(name = "full_name")
    private String fullname;

    @NotNull
    @Size(max = 100)
    @Column(name = "email")
    @Email
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User() {
    }
}