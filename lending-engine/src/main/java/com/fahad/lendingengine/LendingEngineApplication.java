package com.fahad.lendingengine;

import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LendingEngineApplication implements CommandLineRunner {
    @Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LendingEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(1L,"Fahad","Khan",26,"Software Engineer"));
		userRepository.save(new User(2L,"Russel","Ahmed",26,"Pilot"));
	}
}
