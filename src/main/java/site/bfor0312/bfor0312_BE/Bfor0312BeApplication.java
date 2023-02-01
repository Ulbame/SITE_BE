package site.bfor0312.bfor0312_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Bfor0312BeApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(Bfor0312BeApplication.class, args);
	}
}
