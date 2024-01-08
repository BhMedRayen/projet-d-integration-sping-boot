package com.example.freelancer_service.repository;

import com.example.freelancer_service.entity.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer,Long> {

    Freelancer findByEmail(String email);
    @Query(value="select * from freelancer where email=:email",nativeQuery=true)
    Freelancer getByEmail(String email);
}
