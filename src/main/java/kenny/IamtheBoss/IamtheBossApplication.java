package kenny.IamtheBoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IamtheBossApplication {

	public static void main(String[] args) {
		SpringApplication.run(IamtheBossApplication.class, args);
	}

}
