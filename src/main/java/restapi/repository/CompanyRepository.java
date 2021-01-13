package restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.model.Company;
import restapi.model.Etudiant;

public interface CompanyRepository extends JpaRepository<Company,Long>{
	
	Optional<Etudiant> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
