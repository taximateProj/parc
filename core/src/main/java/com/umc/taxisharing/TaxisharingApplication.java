package com.umc.taxisharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.umc.taxisharing.domain")
public class TaxisharingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaxisharingApplication.class, args);
    }
}
