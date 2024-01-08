package com.example.freelancer_service.entity;


import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name="full_freelancer",types = Freelancer.class)
public interface FreelancerProjection extends Projection {
    public long getId();
    public String getFirst_name();
    public String getLast_name();
    public String getEmail();
    public String getPhone();
    public String getPassword();
    public List<String> getExperiance();

    public List<String> getSkills();
    public String getImage();
}
