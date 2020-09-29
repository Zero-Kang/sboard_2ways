package org.zerock.sboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SboardApplication.class, args);
    }

}
