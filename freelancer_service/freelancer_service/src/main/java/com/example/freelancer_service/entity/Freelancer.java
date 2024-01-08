package com.example.freelancer_service.entity;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.util.List;

@Entity
@Enabled @Data @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name="freelancer")
public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name ;
    private String last_name;
    private String email;
    private String phone;
    @ElementCollection
    private List<String> skills;
    @ElementCollection
    private List<String> experiance;
    private String password;
    private String image;



}
