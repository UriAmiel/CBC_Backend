package com.cbc.cbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(RestController.class))
public class CbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbcApplication.class, args);
	}

}
