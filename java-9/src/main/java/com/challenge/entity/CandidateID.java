package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateID implements Serializable {

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Acceleration acceleration;

    @NotNull
    @ManyToOne
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateID that = (CandidateID) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(acceleration, that.acceleration) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, acceleration, company);
    }
}
