package br.dev.drufontael.our_recipes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class OurRecipesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurRecipesApiApplication.class, args);
	}

}
