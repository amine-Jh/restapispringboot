package restapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import restapi.model.User;

@Repository


 public interface UserRepository extends CrudRepository<User, Long> {

	 List<User> findByEmail(String email);

	Object findByUsername(String username);
	

	
}

