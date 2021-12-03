package boot.day1203.mini;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"data.*","boot.day1203.mini"})
@MapperScan("data.*")
public class SpringBootMiniprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMiniprojectApplication.class, args);
	}

}
