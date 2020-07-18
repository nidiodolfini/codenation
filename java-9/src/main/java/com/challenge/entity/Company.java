package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "company")
@EntityListeners(AuditingEntityListener.class)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "id.company", targetEntity = Candidate.class)
    private List<Candidate> candidates;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 50)
    private String slug;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Company() {
    }
}
