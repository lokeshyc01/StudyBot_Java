package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.dao.UserRepository;
import com.app.entities.Role;
import com.app.entities.User;

@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean // equivalent to <bean id ..../> in xml file
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount == null)
		{
			User user = new User();
			
			user.setEmail("admin@gmail.com");
			user.setFirstName("Lokesh");
			user.setLastName("Chaudhari");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("secret@key"));
			userRepository.save(user);
		}
	}

}
