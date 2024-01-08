package com.example.company_service.security;

import com.example.company_service.entity.Company;
import com.example.company_service.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    CompanyRepository userRep;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Company userOptional =userRep.findByEmail(username);
        return new MyUserDetails(userOptional);
    }
}