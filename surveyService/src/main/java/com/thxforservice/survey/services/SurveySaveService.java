package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thxforservice.survey.controllers.RequestSurvey;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyQuestion;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import com.thxforservice.survey.repositories.SurveyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveySaveService {

    private final SurveyInfoRepository infoRepository;
    private final SurveyQuestionRepository questionRepository;
    private final ObjectMapper om;

    public void save(RequestSurvey form) {
        SurveyInfo surveyInfo = SurveyInfo.builder()
                .srvyNm(form.getSrvyNm())
                .srvyReqHr(form.getSrvyReqHr())
                .srvyExpln(form.getSrvyExpln())
                .build();

        try {
            List<Map<String, String>> _criteriaInfo = form.getCriteriaInfo();
            if (_criteriaInfo != null && !_criteriaInfo.isEmpty()) {
                String criteriaInfo = om.writeValueAsString(_criteriaInfo);
                surveyInfo.setCriteriaInfo(criteriaInfo);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        infoRepository.saveAndFlush(surveyInfo);


        /* 질문지 등록하기 S */
        List<SurveyQuestion> questions = form.getQuestions();
        if (questions != null && !questions.isEmpty()) {
            questions.forEach(q -> q.setSurveyInfo(surveyInfo));

            questionRepository.saveAllAndFlush(questions);
        }
        /* 질문지 등록하기 E */

    }
}
