package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge,Long> {

    @Query("SELECT c FROM Challenge c, Acceleration a, Candidate ca, User u " +
            "WHERE c.id = a.id.challenge " +
            "AND  a.id = ca.id.acceleration " +
            "AND ca.id.user = u.id " +
            "AND a.id = :accelerationId " +
            "AND u.id = :userId")
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId,
                                                  @Param("userId") Long userId);

}
