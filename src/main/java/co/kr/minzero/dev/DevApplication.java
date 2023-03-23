package co.kr.minzero.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DevApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DevApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
//        SpringApplication.run(DevApplication.class, args);
        application.run(args);
    }

}
