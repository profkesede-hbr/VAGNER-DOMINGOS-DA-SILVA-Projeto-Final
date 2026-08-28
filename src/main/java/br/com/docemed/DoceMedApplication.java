package br.com.docemed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DoceMedApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoceMedApplication.class, args);
    }
}
