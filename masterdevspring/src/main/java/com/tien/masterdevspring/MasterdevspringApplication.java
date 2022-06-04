package com.tien.masterdevspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MasterdevspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterdevspringApplication.class, args);
    }

}
