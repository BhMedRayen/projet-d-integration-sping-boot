package com.example.company_service.repository;


import com.example.company_service.entity.Company;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Company findByEmail(String email);

    @Query(value="select * from company where email=:email", nativeQuery=true)
    Company getByEmail(String email);
}

