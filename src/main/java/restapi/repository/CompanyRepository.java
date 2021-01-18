package restapi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.model.Company;


public interface CompanyRepository extends JpaRepository<Company,Long>{
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	List<Company> findByAdresse(String adresse);
	
	List<Company> findByTelephone(String telephone);

}
