package com.learning.example.spring.util;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@Slf4j
public class ValidateInput {

    public void validateLogFilePath(String logFileName) {
        log.debug("Validating the program arguments...");
        if (StringUtils.isEmpty(logFileName)) {
            throw new IllegalArgumentException(" Please provide the correct file path.");
        }

        try {
            File file = new ClassPathResource(logFileName).getFile();

            if (!file.exists()) {
                file = new ClassPathResource(logFileName).getFile();
                if (!file.exists()) {
                    file = new File(logFileName);
                }
            }

            if (!file.exists())
                throw new FileNotFoundException("File not present at " + logFileName);
        } catch (IOException e) {
            log.error("File not Found '{}'", logFileName);
        }
    }


}
