package com.challenge.repository;


import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, CandidateId> {


    Optional<Candidate> findByIdUserAndIdCompanyAndIdAcceleration(Long userId, Long companyId, Long accelerationId);

    @Query("SELECT ca FROM Candidate ca, Company co " +
            "WHERE ca.id.company = co.id " +
            "AND co.id = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT ca FROM Candidate ca, Acceleration a " +
            "WHERE ca.id.acceleration = a.id " +
            "AND a.id = :accelerationId")
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);


}
