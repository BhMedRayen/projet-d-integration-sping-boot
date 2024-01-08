package com.example.freelancer_service.freelancerServices;

import com.example.freelancer_service.NodeSync.NodeSync;
import com.example.freelancer_service.entity.Freelancer;
import com.example.freelancer_service.repository.FreelancerRepository;
import com.example.freelancer_service.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.minidev.json.JSONObject;

import java.util.List;

@Service
public class FreelancerServices {

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    NodeSync nodeSync;
    public Freelancer createFreelancer(Freelancer freelancer) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.appendField("first_name", freelancer.getFirst_name());
        jsonObject.appendField("last_name", freelancer.getLast_name());
        jsonObject.appendField("email", freelancer.getEmail());
        jsonObject.appendField("phone", freelancer.getPhone());
        jsonObject.appendField("image", freelancer.getImage());
        freelancer.setPassword(securityConfig.passwordEncoder().encode(freelancer.getPassword()));
        nodeSync.addFreelancer(jsonObject);
        return freelancerRepository.save(freelancer);
    }
    public List<Freelancer> getAllFreelancers(){
        return freelancerRepository.findAll();
    }
    public Freelancer getFreelancerById(Long freelancerId){
        return freelancerRepository.findById(freelancerId).orElse(null);
    }
    public void deleteFreelancer(Long freelancerId){
        freelancerRepository.deleteById(freelancerId);
    }
    public Freelancer updatefreelancer(Long freelancerId, Freelancer updateFreelancer){
        Freelancer existingFreelancer = freelancerRepository.findById(freelancerId).orElse(null);
        if(existingFreelancer != null){
            existingFreelancer.setFirst_name(updateFreelancer.getFirst_name());
            existingFreelancer.setLast_name(updateFreelancer.getLast_name());
            existingFreelancer.setPassword(updateFreelancer.getPassword());
            existingFreelancer.setPhone(updateFreelancer.getPhone());
            existingFreelancer.setSkills(updateFreelancer.getSkills());
            existingFreelancer.setExperiance(updateFreelancer.getExperiance());
            return freelancerRepository.save(existingFreelancer);
        }
        return null;
    }

    public boolean getByEmail(String email) {
       return freelancerRepository.findByEmail(email) != null;
    }

}

