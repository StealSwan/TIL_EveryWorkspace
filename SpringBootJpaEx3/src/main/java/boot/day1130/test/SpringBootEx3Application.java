package boot.day1130.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"mycar.data"})
@EntityScan("mycar.data") //dto가 어느 패키지에 있는지 인식
@EnableJpaRepositories("mycar.data") //dao인식
public class SpringBootEx3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEx3Application.class, args);
	}

}
