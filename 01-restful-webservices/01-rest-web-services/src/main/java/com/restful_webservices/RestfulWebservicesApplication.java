package com.restful_webservices;

import com.restful_webservices.service.AuthorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebservicesApplication.class, args);
	}
    public CommandLineRunner commandLineRunner(AuthorService authorService){
        return runner->{

        };
    }

}
