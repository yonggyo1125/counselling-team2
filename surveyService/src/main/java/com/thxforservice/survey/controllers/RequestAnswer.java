package com.thxforservice.survey.controllers;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestAnswer {

    private Long srvyNo;
    private List<Map<String, String>> answers;
}
