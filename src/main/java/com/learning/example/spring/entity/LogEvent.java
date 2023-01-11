package com.learning.example.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {

    @Id
    private String id;
    private long duration;
    private String host;
    private String type;
    private boolean alertFlag;
    private LogEventState state;
}
