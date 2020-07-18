package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {


    @Query("SELECT DISTINCT co FROM Company co, Candidate ca, Acceleration a " +
            "WHERE co.id = ca.id.company " +
            "AND ca.id.acceleration = a.id " +
            "AND a.id = :accelerationId")
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query("SELECT co FROM Company co, Candidate ca, User u " +
            "WHERE co.id = ca.id.company " +
            "AND ca.id.user = u.id " +
            "AND u.id = :userId")
    List<Company> findByUserId(@Param("userId") Long userId);



}
