package com.thxforservice.survey.services;

import com.thxforservice.survey.controllers.RequestSurvey;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//설문지 등록, 수정
@Service
@RequiredArgsConstructor
public class SurveySaveService {

    private final SurveyInfoRepository infoRepository;

    public void save(RequestSurvey form) {




    }
}
