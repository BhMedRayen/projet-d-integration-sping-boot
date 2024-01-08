package com.example.offre_service.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full_offre",types = Offre.class)
public interface OffreProjection extends Projection {
    public Long getId();
    public String getDescription();
    public Long getCompanyId();
}
