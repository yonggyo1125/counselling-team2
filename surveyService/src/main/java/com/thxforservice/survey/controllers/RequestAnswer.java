package com.thxforservice.survey.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestAnswer {

    private Long srvyNo;
    private List<Map<Long, Integer>> answers;

}
