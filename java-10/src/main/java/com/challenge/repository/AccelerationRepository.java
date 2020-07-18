package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    @Query("SELECT a FROM Acceleration a, Candidate ca, Company co " +
            "WHERE a.id = ca.id.acceleration " +
            "AND ca.id.company = co.id " +
            "AND co.id = :companyId")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);



}
