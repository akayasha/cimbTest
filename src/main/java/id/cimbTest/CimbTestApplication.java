package id.cimbTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CimbTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CimbTestApplication.class, args);
	}

}
