package pl.bykowski.springbootcaffe;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringbootCaffeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCaffeApplication.class, args);
    }



}
