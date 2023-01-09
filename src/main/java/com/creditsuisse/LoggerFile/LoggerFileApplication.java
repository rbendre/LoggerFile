package com.creditsuisse.LoggerFile;


import com.creditsuisse.LoggerFile.Service.EventLoggerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerFileApplication  implements CommandLineRunner 
{
	@Autowired
	EventLoggerService eventLogggerService;
	
	 private  static  final Logger log = LoggerFactory.getLogger(LoggerFileApplication.class);
	
	public static void main(String[] args) {
		SpringApplication mainApp = new SpringApplication(LoggerFileApplication.class);
		//Overriding run method so that we can call our custom functions
		mainApp.run(args);
				
	}

	@Override
	public void run(String... args) {
		log.info("Starting to execute...");
		if(args==null || args.length!=1) {
			throw new IllegalArgumentException("Invalid argument passed. File location must be passed to execute the program");
		}
		//Parsing and Storing the server logs
		eventLogggerService.parseAndStoreEvents(args[0]);
		
		//Printing the logs saved in database
		eventLogggerService.printAllDatabaseEvents();
		System.exit(0);
		
	}
}
