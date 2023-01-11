package com.learning.example.spring.service;

import com.learning.example.spring.processor.LogProcessor;
import com.learning.example.spring.repository.LogEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
@RequiredArgsConstructor
public class LogAnalysisService {
    private final LogProcessor logProcess;
    private final LogEventRepository logEventRepository;

    public void execute(String logFilePath) throws IOException {
        logProcess.parseAndSave(logFilePath);
    }

    public void printAllDatabaseEvents() {
        log.info("Printing save log events in database");
        logEventRepository.findAll().stream().forEach(System.out::println);
        log.info("______________________END_______________________");
    }
}
