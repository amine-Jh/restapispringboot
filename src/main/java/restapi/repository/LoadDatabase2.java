/*

package restapi.repository;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import restapi.model.Etudiant;


@Configuration
class LoadDatabase2 {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase2.class);

  @Bean
  CommandLineRunner initDatabase2(EtudiantRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Etudiant("Bilbo Baggins", "burglar","hdhdhdhdh", "gajhkzeaze", "jhkhazhelakzh","hhdhhddh")));
      log.info("Preloading " + repository.save(new Etudiant("Frodo Baggins", "thief","hdhdhdhdh", "hjkljm", "gjhklmahkla","jdjdjdjdj ")));
    };
  }
}

*/