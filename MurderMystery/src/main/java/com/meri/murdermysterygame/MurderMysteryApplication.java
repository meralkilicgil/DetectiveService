package com.meri.murdermysterygame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MurderMysteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MurderMysteryApplication.class, args);
    }
}
