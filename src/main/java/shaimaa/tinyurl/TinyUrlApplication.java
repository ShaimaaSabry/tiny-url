package shaimaa.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class TinyUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyUrlApplication.class);
    }
}
