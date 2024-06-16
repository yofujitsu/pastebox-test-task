package com.yofujitsu.pastebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PasteboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasteboxApplication.class, args);
    }

}
