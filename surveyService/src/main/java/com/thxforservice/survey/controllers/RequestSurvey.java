package com.thxforservice.survey.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thxforservice.survey.entities.SurveyQuestion;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestSurvey {

    private Long srvyNo; // 수정시 필요

    private String mode;

    private String gid = UUID.randomUUID().toString();

    @NotBlank
    private String srvyNm; // 검사 이름

    private Boolean srvyUse; // 검사 사용 여부

    private String srvyReqHr;

    private String srvyExpln;

    private String criteriaInfo;

    private List<SurveyQuestion> questions;
}
