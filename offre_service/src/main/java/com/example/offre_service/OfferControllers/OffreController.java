package com.example.offre_service.OfferControllers;


import com.example.offre_service.OffreServices.OffreServices;
import com.example.offre_service.entity.Offre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/offers")
public class OffreController {
    @Autowired
    private OffreServices offreServices;
    @PostMapping("/create/{companyId}")
    public ResponseEntity<Offre> createOffre(@RequestBody Offre offre, @PathVariable Long companyId)
    {
        Offre createOffre = offreServices.createOffre(offre,companyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOffre);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable Long id){
        Offre offre = offreServices.getOffreById(id);
        if(offre != null){
            return ResponseEntity.ok(offre);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getByCompanyId/{Companyid}")
    public ResponseEntity<List<Offre>> getOffreByCompanyId(@PathVariable Long Companyid){
        List<Offre> offre = offreServices.getOffreByCompanyId(Companyid);
        if(offre != null){
            return ResponseEntity.ok(offre);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable  Long id,@RequestBody Offre updateOffre){
        Offre offre = offreServices.updateOffre(id,updateOffre);
        if(offre != null){
            return ResponseEntity.ok(offre);
        }
        else {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/setOffreDone/{id}")
    public ResponseEntity<Offre> setOffreDone(@PathVariable Long id){
        Offre offre = offreServices.setOffreDone(id);
        if(offre != null){
            return ResponseEntity.ok(offre);
        }
        else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Offre> deletOffre(@PathVariable Long id){
        offreServices.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<Offre>> getAllOffres(){
        List<Offre> offers = offreServices.getAllOffers();
        if(!offers.isEmpty()){
            return ResponseEntity.ok(offers);
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }
}
