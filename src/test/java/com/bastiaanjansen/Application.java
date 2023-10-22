package com.bastiaanjansen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApiVersioning
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
