package com.learning.example.spring.processor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.learning.example.spring.entity.LogEvent;
import com.learning.example.spring.entity.LogEventState;
import com.learning.example.spring.model.LogEventDetail;
import com.learning.example.spring.repository.LogEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class LogProcessor {
    private final LogEventRepository logEventRepository;

    public void parseAndSave(String fileName) throws IOException {

        Gson gson = new Gson();
        List<LogEvent> logEvents = new ArrayList<>();
        Map<String, LogEventDetail> events = new HashMap<>();
        try (Stream<LogEventDetail> stream = Files.lines(Paths.get(fileName)).parallel().filter(StringUtils::hasText)
                .map(s -> gson.fromJson(s, LogEventDetail.class))) {
            stream.forEach(o -> {

                if (!StringUtils.isEmpty(o.getId())) {
                    if (!events.containsKey(o.getId())) {
                        events.put(o.getId(), o);
                    } else {
                        LogEventDetail startEvent = events.get(o.getId());
                        long timeDiff = Math.abs(o.getTimestamp() - startEvent.getTimestamp());
                        Boolean flag = timeDiff > 4 ? Boolean.TRUE : Boolean.FALSE;
                        logEvents.add(buildLogEvent(o, timeDiff, flag));
                        events.remove(o.getId());
                    }
                }
            });

          List<LogEvent> l = logEventRepository.saveAll(logEvents);
          log.info("Number of log events save in dataBase "+l.size());

        } catch (IOException | IllegalArgumentException | JsonSyntaxException e) {
            log.error("encounter the issue", e);
        } catch (Exception e) {
            log.error("Exception" + e);
        }
    }


    private LogEvent buildLogEvent(LogEventDetail detail, long duration, Boolean flag) {

        return LogEvent.builder()
                .id(detail.getId())
                .type(detail.getType())
                .alertFlag(flag)
                .host(detail.getHost())
                .state(LogEventState.valueOf(detail.getState()))
                .duration(duration)
                .build();

    }
}
