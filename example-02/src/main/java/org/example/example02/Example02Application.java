package org.example.example02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Example02Application {

    public static void main(String[] args) {
        SpringApplication.run(Example02Application.class, args);
        GetTransaction2 getTransaction2=new GetTransaction2();
    }

}
