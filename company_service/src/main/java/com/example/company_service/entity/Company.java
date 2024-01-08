package com.example.company_service.entity;


import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;
@Entity
@Enabled @Data @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;
    private String website;
    private String description;
    private String password;
    @ElementCollection
    private List<String> localisation;
    private String adresse;
    private String image;
    
}
