package com.example.freelancer_service.freelancerController;

import com.example.freelancer_service.Payload.Credentials;
import com.example.freelancer_service.entity.Freelancer;
import com.example.freelancer_service.freelancerServices.FreelancerServices;
import com.example.freelancer_service.jwt.JwtTokenUtil;
import com.example.freelancer_service.repository.FreelancerRepository;
import com.example.freelancer_service.security.UserDetailsImpl;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancers")
public class FreelancerController {

    @Autowired
    FreelancerServices freelancerServices;

    @Autowired
    FreelancerRepository freelancerRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsImpl userservice;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @PostMapping("/create")
    public ResponseEntity<Freelancer> createFreelancer(@RequestBody Freelancer freelancer) {
        Freelancer createFreelancer = freelancerServices.createFreelancer(freelancer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createFreelancer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Freelancer> getFreelancerById(@PathVariable Long id) {
        Freelancer freelancer = freelancerServices.getFreelancerById(id);
        if (freelancer != null) {
            return ResponseEntity.ok(freelancer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Freelancer> updateFreelancer(@PathVariable Long id, @RequestBody Freelancer updateFreelancer) {
        Freelancer freelancer = freelancerServices.updatefreelancer(id, updateFreelancer);
        if (freelancer != null) {
            return ResponseEntity.ok(freelancer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFreelancer(@PathVariable Long id) {
        freelancerServices.deleteFreelancer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Freelancer>> getAllFreelancers(){

        List<Freelancer> freelancers = freelancerServices.getAllFreelancers();
        if(!freelancers.isEmpty()){
            return ResponseEntity.ok(freelancers);
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Credentials parametre){
        try {
            if(!freelancerServices.getByEmail(parametre.getEmail())) {
                return new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND);
            }
            Authentication authsuser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(parametre.getEmail(), parametre.getPassword()));
            UserDetails user_detailts=userservice.loadUserByUsername(parametre.getEmail());
            String token=jwtTokenUtil.generateToken(user_detailts);
            JSONObject json=new JSONObject();
            json.appendField("user",freelancerRepository.getByEmail(parametre.getEmail()));
            json.appendField("token",token);
            return ResponseEntity.ok(json);
        }catch(BadCredentialsException e) {
            return new ResponseEntity<String>("Incorrect email or password",HttpStatus.NOT_FOUND);
        }
    }
    
}