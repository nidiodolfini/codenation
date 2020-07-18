package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "submission")
@EntityListeners(AuditingEntityListener.class)
public class Submission implements Serializable {

    @EmbeddedId
    private SubmissionID id;

    @NotNull
    @Column(name = "score")
    private Float score;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Submission() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(score, that.score) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, createdAt);
    }
}