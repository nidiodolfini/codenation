package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "acceleration")
@EntityListeners(AuditingEntityListener.class)
public class Acceleration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "id.acceleration", targetEntity = Candidate.class)
    private List<Candidate> candidates;

    @ManyToOne
    private Challenge challenge;

    @NotNull
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 50)
    @Column(name = "slug")
    private String slug;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Acceleration() {
    }
}