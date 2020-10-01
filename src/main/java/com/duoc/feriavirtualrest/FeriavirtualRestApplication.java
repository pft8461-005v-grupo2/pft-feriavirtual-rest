package com.duoc.feriavirtualrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.duoc.feriavirtualrest.repository")
public class FeriavirtualRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeriavirtualRestApplication.class, args);
    }

}
