package io.ugurh.kops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("io.ugurh.kops.repository")
public class KopsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KopsApplication.class, args);
    }

}
