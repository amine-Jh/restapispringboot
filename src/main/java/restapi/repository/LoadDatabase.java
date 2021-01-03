/*package restapi.repository;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import restapi.model.User;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new User("Bilbo Baggins", "burglar","amine@djjd","user")));
      log.info("Preloading " + repository.save(new User("Frodo Baggins", "thief","amine@gmail","user")));
    };
  }
}*/