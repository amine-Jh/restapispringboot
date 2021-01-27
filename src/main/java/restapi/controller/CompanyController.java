package restapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restapi.exception.UserNotFoundException;
import restapi.model.Company;

import restapi.repository.CompanyRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/resources")

@PreAuthorize("hasRole('ADMIN') or hasRole('ETUDIANT') or hasRole('COMPANY') ")
public class CompanyController {	

@Autowired
private final CompanyRepository repository ;

	@Autowired
	PasswordEncoder encoder;


  public CompanyController(CompanyRepository repository) {
		this.repository = repository;
	}


  @GetMapping("/companies")
  List<Company> all() {
    return (List<Company>) repository.findAll();
  }
  
  

  @PostMapping("/companies")
  Company newCompany(@RequestBody Company newCompany) {
	  
    return repository.save(newCompany);
  }



  @GetMapping("/companies/{id}")
  Company Getone(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  @PutMapping("/companies/{id}")
  Company replaceCompany(@RequestBody Company newEmployee, @PathVariable Long id) {

    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setUsername(newEmployee.getUsername());
        employee.setAdresse( newEmployee.getAdresse());
        employee.setType( newEmployee.getType());
        employee.setTelephone(newEmployee.getTelephone() );
        employee.setPassword( encoder.encode(newEmployee.getPassword()) );
        employee.setEmail(newEmployee.getEmail());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/companies/{id}")
  void deleteCompany(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
