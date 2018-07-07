package com.message.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
public class ServiceApplication {
	private static final Logger logger= LoggerFactory.getLogger(ServiceApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext cxt = SpringApplication.run(ServiceApplication.class, args);
		logger.info("Running on port: "+cxt.getEnvironment().getProperty("local.server.port"));
	}
}
