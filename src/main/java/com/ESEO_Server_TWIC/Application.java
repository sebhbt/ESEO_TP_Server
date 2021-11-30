package com.ESEO_Server_TWIC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main( String[] args ) {
    	SpringApplication.run(Application.class, args);
        System.out.println( "Application demarree!" );
    }
}
