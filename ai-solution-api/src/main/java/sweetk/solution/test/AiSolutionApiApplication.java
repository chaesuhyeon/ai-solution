package sweetk.solution.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableMongoAuditing
@EnableJpaAuditing
@EnableAsync
public class AiSolutionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiSolutionApiApplication.class, args);
	}

}
