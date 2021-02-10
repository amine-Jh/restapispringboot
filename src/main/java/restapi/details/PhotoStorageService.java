package restapi.details;
import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import restapi.model.PhotoInfo;
import restapi.repository.PhotoInfoRepository;



@Service
public class PhotoStorageService {

	
	  @Autowired
	  private PhotoInfoRepository fileDBRepository;

	  public PhotoInfo store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    PhotoInfo FileDB = new PhotoInfo(fileName, file.getContentType(), file.getBytes());

	    return fileDBRepository.save(FileDB);
	  }

	  public PhotoInfo getFile(String id) {
	    return fileDBRepository.findById(id).get();
	  }
	  
	  public Stream<PhotoInfo> getAllFiles() {
	    return fileDBRepository.findAll().stream();
	  }
}
