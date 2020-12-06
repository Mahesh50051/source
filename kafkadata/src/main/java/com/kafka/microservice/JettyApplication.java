package com.kafka.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring application startup class that wire and configure this MicroService
 * 
 * Parameters used in the initialization comes from the application.properties file
 * 
 * @author mahesh
 */

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class JettyApplication
{

	public static void main(String[] args)
	{
		// lookup the name of this class ... MethodHandles.lookup().lookupClass()
		SpringApplication.run(JettyApplication.class, args);
	}

}
