package com.example.offre_service.OffreServices;


import com.example.offre_service.OffreRepository.OffreRepository;
import com.example.offre_service.entity.Offre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreServices {
    @Autowired
    private OffreRepository offreRepository;
    public Offre createOffre(Offre offre, Long companyId){
        offre.setDone(false);
        offre.setCompanyId(companyId);
        return   offreRepository.save(offre);
    }
    public List<Offre> getAllOffers(){
        return offreRepository.findAll();
    }

    public Offre getOffreById(Long id){
        return offreRepository.findById(id).orElse(null);
    }
    public List<Offre> getOffreByCompanyId(Long CompanyId){
        return offreRepository.findByCompanyId(CompanyId);
    }
    public void deleteOffre(Long id){
        offreRepository.deleteById(id);
    }
    public Offre updateOffre(Long offreId,Offre updateOffre){
        Offre existingOffre =offreRepository.findById(offreId).orElse(null);
        if(existingOffre != null) {
            existingOffre.setDescription(updateOffre.getDescription());
            return offreRepository.save(existingOffre);
        }
        return null;
    }
    public Offre setOffreDone(Long offreId){
        Offre existingOffre =offreRepository.findById(offreId).orElse(null);
        if(existingOffre != null) {
            existingOffre.setDone(true);
            return offreRepository.save(existingOffre);
        }
        return null;
    }

}
