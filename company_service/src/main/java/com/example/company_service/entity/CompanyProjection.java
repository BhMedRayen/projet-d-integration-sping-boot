package com.example.company_service.entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "full_company",types = Company.class)
public interface CompanyProjection extends Projection  {
    public long getId();
    public String getName();
    public String getEmail();
    public String getPhone();
    public String getWebsite();
    public String getDescription();
    public String getPassword();
    public String getAdresse();
    public String getImage();
    public List<String> getLocalisation();
}
