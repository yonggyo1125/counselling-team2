package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thxforservice.survey.controllers.RequestSurvey;
import com.thxforservice.survey.entities.QSurveyQuestion;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyQuestion;
import com.thxforservice.survey.exceptions.SurveyInfoNotFoundException;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import com.thxforservice.survey.repositories.SurveyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

//설문지 등록, 수정
@Service
@Transactional
@RequiredArgsConstructor
public class SurveySaveService {

    private final SurveyInfoRepository infoRepository;
    private final SurveyQuestionRepository questionRepository;
    private final ObjectMapper om;
    private final ModelMapper modelMapper;

    public void save(RequestSurvey form) {
        Long srvyNo = form.getSrvyNo();
        String mode = Objects.requireNonNullElse(form.getMode(), "write");

        QSurveyQuestion surveyQuestion = QSurveyQuestion.surveyQuestion;

        SurveyInfo data = null;
        if (mode.equals("update") && srvyNo != null) {
            data = infoRepository.findById(srvyNo).orElseThrow(SurveyInfoNotFoundException::new);

            data.setSrvyNm(form.getSrvyNm());
            data.setSrvyUse(form.getSrvyUse());
            data.setSrvyReqHr(form.getSrvyReqHr());
            data.setSrvyExpln(form.getSrvyExpln());
            List<Map<String, String>> _criteriaInfo = form.getCriteriaInfo();
            if (_criteriaInfo != null) {
                try {
                    data.setCriteriaInfo(om.writeValueAsString(_criteriaInfo));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            // 기 등록된 문항 데이터 삭제
            List<SurveyQuestion> formerItems = (List<SurveyQuestion>)questionRepository.findAll(surveyQuestion.surveyInfo.srvyNo.eq(srvyNo));
            questionRepository.deleteAll(formerItems);
            questionRepository.flush();
        } else {
            data = modelMapper.map(form, SurveyInfo.class);
        }

        infoRepository.saveAndFlush(data);

        List<SurveyQuestion> newItems = form.getQuestions();
        for (SurveyQuestion item : newItems) {
            item.setSurveyInfo(data);
        }

        questionRepository.saveAllAndFlush(newItems);
    }
}
