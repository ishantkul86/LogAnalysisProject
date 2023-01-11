package com.learning.example.spring;

import com.learning.example.spring.service.LogAnalysisService;
import com.learning.example.spring.util.ValidateInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.FileNameMap;

@SpringBootApplication
//@EnableAsync
@Slf4j
@RequiredArgsConstructor
public class LogAnalysisApplication {

	private final LogAnalysisService logAnalysisService;

	private final ValidateInput validator;
	private static String fileName;

	public static void main(String[] args) {
		if(args==null || args.length!=1) {
			throw new IllegalArgumentException("Invalid argument passed. File location must be passed to execute the program");
		}
		fileName=args[0];
		SpringApplication.run(LogAnalysisApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() throws IOException {
		log.info("Starting to event execute ...");
		Resource resource = new ClassPathResource(fileName);
		//Validate the file path
		validator.validateLogFilePath(resource.getFile().getPath());

		//Parsing  and Storing data from log file
		logAnalysisService.execute(resource.getFile().getPath());

		//Printing saved data in database
		logAnalysisService.printAllDatabaseEvents();
		System.exit(0);

	}
}
