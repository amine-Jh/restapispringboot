package restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import restapi.model.Etudiant;
public interface EtudiantRepository  extends JpaRepository<Etudiant,Long>{

	Optional<Etudiant> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
}

