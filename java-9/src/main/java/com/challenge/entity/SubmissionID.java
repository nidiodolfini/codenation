package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubmissionID implements Serializable {

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Challenge challenge;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionID that = (SubmissionID) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(challenge, that.challenge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, challenge);
    }
}