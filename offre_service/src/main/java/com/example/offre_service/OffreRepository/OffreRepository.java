package com.example.offre_service.OffreRepository;


import com.example.offre_service.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OffreRepository extends JpaRepository<Offre,Long> {
    List<Offre> findByCompanyId(Long companyId);
}
