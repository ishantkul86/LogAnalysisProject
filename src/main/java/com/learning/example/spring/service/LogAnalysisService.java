package com.learning.example.spring.service;/*
package com.learning.example.spring.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class LogEventsService {

    public void execute(String... args) {
        AppContext context = AppContext.getInstance();
        System.out.println("Context = " + context);
        verifyInputFile.validateFilePath(context, args);
        analyseLog.parseLogsAndCreateAlert(context);

    }

}
*/


import com.learning.example.spring.processor.LogProcessor;
import com.learning.example.spring.repository.LogEventRepository;
import com.learning.example.spring.util.ValidateInput;
import lombok.AllArgsConstructor;
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
