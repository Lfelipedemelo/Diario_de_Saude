package br.com.diario_de_saude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class DiarioDeSaudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiarioDeSaudeApplication.class, args);
	}

}
