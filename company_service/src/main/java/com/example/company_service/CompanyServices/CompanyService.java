package com.example.company_service.CompanyServices;
import com.example.company_service.entity.Company;
import com.example.company_service.nodeSync.NodeSync;
import com.example.company_service.repository.CompanyRepository;
import com.example.company_service.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import net.minidev.json.JSONObject;


@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    NodeSync nodeSync;
    @Autowired
    SecurityConfig securityConfig;
    public Company createCompany(Company company){
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("name" , company.getName());
        jsonObject.appendField("email",company.getEmail());
        jsonObject.appendField("phone",company.getPhone());
        jsonObject.appendField("website",company.getWebsite());
        jsonObject.appendField("description",company.getDescription());
        jsonObject.appendField("image",company.getImage());
        nodeSync.addCompany(jsonObject);
        company.setPassword(securityConfig.passwordEncoder().encode(company.getPassword()));
        return companyRepository.save(company);
    }
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    public void deleteCompany(Long companyId){
        companyRepository.deleteById(companyId);
    }
    public Company getCompanyById(Long companyId){
        return companyRepository.findById(companyId).orElse(null);
    }

    public Company updateCompany(Long CompanyId,Company updateCompany){
        Company existingCompany = companyRepository.findById(CompanyId).orElse(null);
        if(existingCompany!=null){
            existingCompany.setName(updateCompany.getName());
            existingCompany.setPhone(updateCompany.getPhone());
            existingCompany.setWebsite(updateCompany.getWebsite());
            existingCompany.setImage(updateCompany.getImage());
            existingCompany.setAdresse(updateCompany.getAdresse());
            existingCompany.setLocalisation(updateCompany.getLocalisation());
            existingCompany.setDescription(updateCompany.getDescription());
            existingCompany.setPassword(updateCompany.getPassword());
            return companyRepository.save(existingCompany);
        }
        return null;
    }

    public String getCompanyNameById(Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        return companyOptional.map(Company::getName).orElse("Unknown Company");
    }

    public boolean getByEmail(String email) {
        return companyRepository.findByEmail(email) != null;
    }
}
