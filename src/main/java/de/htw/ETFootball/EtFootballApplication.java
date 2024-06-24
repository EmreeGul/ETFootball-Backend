package de.htw.ETFootball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class EtFootballApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtFootballApplication.class, args);
	}

}
