package dio.projetos.padroes.projeto.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot gerado via Spring Initializr.
 * Módulos selecionados:
 * - Spring Data JPA
 * - Spring Web
 * -H2 Database
 * - OpenFeing
 *
 * @author Hayane
 */

@EnableFeignClients
@SpringBootApplication

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
