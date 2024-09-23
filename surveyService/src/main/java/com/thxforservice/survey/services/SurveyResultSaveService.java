package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import com.thxforservice.survey.controllers.RequestAnswer;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyResult;
import com.thxforservice.survey.exceptions.SurveyInfoNotFoundException;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import com.thxforservice.survey.repositories.SurveyResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SurveyResultSaveService {
    private final SurveyInfoRepository infoRepository;
    private final SurveyResultRepository resultRepository;
    private final MemberUtil memberUtil;
    private final ObjectMapper om;

    public void save(RequestAnswer form) {
        Member member = memberUtil.getMember();
        Long srvyNo = form.getSrvyNo();
        SurveyInfo surveyInfo = infoRepository.findById(srvyNo).orElseThrow(SurveyInfoNotFoundException::new);

        SurveyResult surveyResult = SurveyResult.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .surveyInfo(surveyInfo)
                .build();

        List<Map<Long, Integer>> answers = form.getAnswers();
        if (answers != null && !answers.isEmpty()) {

            try {
                String answerData = om.writeValueAsString(answers);
                surveyResult.setAnswerData(answerData);
                surveyResult.setTotScr(getScore(srvyNo, answers));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public Long getScore(Long srvyNo, List<Map<Long, Integer>> answerData) {


        return 0L;
    }
}
