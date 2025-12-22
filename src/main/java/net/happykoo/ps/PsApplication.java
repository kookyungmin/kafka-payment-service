package net.happykoo.ps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PsApplication {

  public static void main(String[] args) {
    SpringApplication.run(PsApplication.class, args);
  }

}
