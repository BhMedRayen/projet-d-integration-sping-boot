    package com.example.company_service.CompanyController;
    import com.example.company_service.CompanyServices.CompanyService;
    import com.example.company_service.Payload.Credentials;
    import com.example.company_service.entity.Company;
    import com.example.company_service.jwt.JwtTokenUtil;
    import com.example.company_service.repository.CompanyRepository;
    import com.example.company_service.security.UserDetailsImpl;
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
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/companies")
    public class CompanyController {
        @Autowired
        private CompanyService companyService;
        @Autowired
        private CompanyRepository companyRepository;


        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserDetailsImpl userservice;

        @Autowired
        JwtTokenUtil jwtTokenUtil;


        @PostMapping("/create")
        public ResponseEntity<Company> createCompany(@RequestBody Company company) {
            Company createCompany = companyService.createCompany(company);

            return ResponseEntity.status(HttpStatus.CREATED).body(createCompany);
        }
        @GetMapping("/{id}")
        public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
            Company company = companyService.getCompanyById(id);
            if (company != null) {
                return ResponseEntity.ok(company);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        @PutMapping("/update/{id}")
        public ResponseEntity<Company> updateCompany(@PathVariable Long id,@RequestBody Company updateCompany){
            Company company = companyService.updateCompany(id,updateCompany);
            if (company != null) {
                return ResponseEntity.ok(company);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
            companyService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        }
        @GetMapping("/all")
        public ResponseEntity<List<Company>> getAllCompanies() {
            List<Company> companies = companyService.getAllCompanies();
            if (!companies.isEmpty()) {
                return ResponseEntity.ok(companies);
            } else {
                return ResponseEntity.noContent().build();
            }
        }

        @GetMapping("/getCompanyNameById/{companyId}")
        public ResponseEntity<String> getCompanyNameById(@PathVariable("companyId") Long companyId) {
            String companyName = companyService.getCompanyNameById(companyId);

            if (companyName != null) {
                return ResponseEntity.ok(companyName);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/login")
        public ResponseEntity<?> authenticate(@RequestBody Credentials parametre){
            try {
                if(!companyService.getByEmail(parametre.getEmail())) {
                    return new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND);
                }
                Authentication authsuser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(parametre.getEmail(), parametre.getPassword()));
                UserDetails user_detailts=userservice.loadUserByUsername(parametre.getEmail());
                String token=jwtTokenUtil.generateToken(user_detailts);
                JSONObject json=new JSONObject();
                json.appendField("user",companyRepository.getByEmail(parametre.getEmail()));
                json.appendField("token",token);
                return ResponseEntity.ok(json);
            }catch(BadCredentialsException e) {
                return new ResponseEntity<String>("Incorrect email or password",HttpStatus.NOT_FOUND);
            }
        }

    }
