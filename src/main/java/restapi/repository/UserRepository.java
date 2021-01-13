package restapi.repository;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import restapi.model.User;

@Repository


 public interface UserRepository extends CrudRepository<User, Long> {


	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	

	
}

