package com.sample.funcionarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        //System.out.println( "Hello World!" );
    }
}
