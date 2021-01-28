package restapi.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.iap.Response;

import restapi.exception.UserNotFoundException;
import restapi.model.Company;
import restapi.model.Etudiant;
import restapi.model.User;
import restapi.payload.MessageResponse;
import restapi.payload.PostuleRequest;
import restapi.repository.CompanyRepository;
import restapi.repository.EtudiantRepository;
import restapi.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/resources")


@PreAuthorize("hasRole('ADMIN')  or hasRole('ETUDIANT')")
public class EtudiantController {
	

@Autowired
  private final EtudiantRepository repository;

@Autowired
PasswordEncoder encoder;

@Autowired
private final CompanyRepository crepository;



  EtudiantController(EtudiantRepository repository  ,CompanyRepository crepository ) {
    this.repository = repository;
	this.crepository = crepository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  
  @GetMapping("/etudiants")
  
  
  List<Etudiant> all() {
    return (List<Etudiant>) repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/etudiants")
  User newEmployee(@RequestBody Etudiant newEtudiant) {
	  
    return repository.save(newEtudiant);
  }
  
 

  @PostMapping("/postuler/{ids}/company/{id}")
  Etudiant Getcompany(   @PathVariable  Long ids,  @PathVariable  Long id ) {
	  	
	  
	   Etudiant nn= repository.findById(ids).orElseThrow(() -> new UserNotFoundException(id))   ;
	  Company mm= crepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	  
	  
	   
	   nn.getCompanies().add(mm);
	   System.out.println(nn.getCompanies().toString());
	   repository.save(nn);
	  
	     
    return repository.findById(ids)
      .orElseThrow(() -> new UserNotFoundException(id));
  }
  
  @PostMapping("/depostuler/{ids}/company/{id}")
  Etudiant depostuler(   @PathVariable  Long ids,  @PathVariable  Long id ) {
	  	
	 
	  Etudiant nn= repository.findById(ids).orElseThrow(() -> new UserNotFoundException(id))   ;
	  Company mm= crepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	  nn.getCompanies().remove(mm);
	   repository.save(nn);
	  
	     
    return repository.findById(ids)
      .orElseThrow(() -> new UserNotFoundException(id));
  }
  
  
  
  
  

  
  

  // Single item

  @GetMapping("/etudiants/{id}")
  Etudiant one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  @PutMapping("/etudiants/{id}")
  public ResponseEntity<?> updateUser(
      @PathVariable(value = "id") Long userId, @Validated @RequestBody Etudiant newEmployee)
      throws ConfigDataResourceNotFoundException {
	  
 	 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
 	
    Etudiant employee =
        repository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
    	String password=employee.getPassword();
    if(passwordEncoder.matches(newEmployee.getPassword(), password)) {
		employee.setName(newEmployee.getName());
		  /* employee.setPassword(encoder.encode(newEmployee.getPassword()));*/
    employee.setEmail(newEmployee.getEmail());
    employee.setAnnee( newEmployee.getAnnee()   );
    employee.setTelephone(newEmployee.getTelephone());
    employee.setFilliere(newEmployee.getFilliere());
    
	 }
    else {
    	return ResponseEntity.ok(  new MessageResponse("mot de passe incorrect")  );
    }
    
    final Etudiant updatedUser = repository.save(employee);
    return ResponseEntity.ok(updatedUser);
    
  }
  
  
  
  
  
  
  
  @PutMapping("/etudiants/updatePassword/{id}")
  User updatePassword(@RequestBody Etudiant newEmployee, @PathVariable Long id) {
	  
    return repository.findById(id)
      .map(employee -> {
        String  password=   employee.getPassword();
        if( password== newEmployee.getPassword()  )
       employee.setPassword(encoder.encode(newEmployee.getPassword()));
        
        
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }
  
  

  @DeleteMapping("/etudiants/{id}")
  void deleteEtudiant(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
