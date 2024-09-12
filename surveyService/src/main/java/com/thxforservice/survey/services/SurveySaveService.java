package com.thxforservice.survey.services;

import com.thxforservice.survey.controllers.RequestSurvey;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

//설문지 등록, 수정
@Service
@RequiredArgsConstructor
public class SurveySaveService {

    private final SurveyInfoRepository infoRepository;
    private final ModelMapper modelMapper;

    public void save(RequestSurvey form) {
        Long srvyNo = form.getSrvyNo();
        String mode = form.getMode();

    }
}
