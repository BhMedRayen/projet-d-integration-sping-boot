package com.example.offre_service.entity;



import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Enabled
@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "offre")
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "CompanyId")
    private Long companyId;
    private boolean done;
    private String titre;
    private String topic;
//    @ElementCollection
//    private List<String> category;// Ajoutez une propriété pour stocker la catégorie des boutons radio
      private String category;
}


