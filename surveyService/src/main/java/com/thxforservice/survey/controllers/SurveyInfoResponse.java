package com.thxforservice.survey.controllers;

import com.thxforservice.survey.entities.SurveyInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SurveyInfoResponse {

    private SurveyInfo surveyInfo;
    private List<Long> itemNos;
}
