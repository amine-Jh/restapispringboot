package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import restapi.model.PhotoInfo;

@Repository
public interface PhotoInfoRepository extends JpaRepository<PhotoInfo, String> {

}

