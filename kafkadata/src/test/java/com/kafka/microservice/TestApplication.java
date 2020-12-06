package com.kafka.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = { "com.kafka.microservice.*" })
public class TestApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TestApplication.class, args);
	}

}