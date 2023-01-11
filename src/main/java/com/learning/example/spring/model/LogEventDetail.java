package com.learning.example.spring.model;


import lombok.Data;

@Data
public class LogEventDetail {

    private String id;
    private String state;
    private String type;
    private String host;
    private Long timestamp;

}
