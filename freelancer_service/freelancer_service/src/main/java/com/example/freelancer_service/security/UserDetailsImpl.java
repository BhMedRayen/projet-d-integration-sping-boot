package com.example.freelancer_service.security;

import com.example.freelancer_service.entity.Freelancer;
import com.example.freelancer_service.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    FreelancerRepository userRep;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Freelancer userOptional =userRep.findByEmail(username);
        return new MyUserDetails(userOptional);
    }
}