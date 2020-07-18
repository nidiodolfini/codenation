package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u, Candidate c, Acceleration a " +
            "WHERE u.id = c.id.user " +
            "AND c.id.acceleration = a.id " +
            "AND a.name = :accelerationName")
    List<User> findByAccelerationName(@Param("accelerationName") String name);

    @Query("SELECT u FROM User u, Candidate ca, Company co " +
            "WHERE u.id = ca.id.user " +
            "AND ca.id.company = co.id " +
            "AND co.id = :companyId")
    List<User> findByCompanyId(@Param("companyId") Long companyId);

    User findUserByFullNameAndEmailAndNicknameAndPassword(String name, String email, String nick, String password);


}
