package com.ESEO_TP_Server_I3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main( String[] args ) {
    	SpringApplication.run(Application.class, args);
        System.out.println( "Application demarree!" );
    }
}
