package boot.day1202.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"boot.market.*", "boot.book.*"})
public class SpringBootMybatisEx4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisEx4Application.class, args);
	}

}
