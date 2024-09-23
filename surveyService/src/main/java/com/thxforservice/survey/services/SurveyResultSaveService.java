package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import com.thxforservice.survey.controllers.RequestAnswer;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyQuestion;
import com.thxforservice.survey.entities.SurveyResult;
import com.thxforservice.survey.repositories.SurveyResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SurveyResultSaveService {
    private final SurveyInfoService infoService;
    private final SurveyResultRepository resultRepository;
    private final MemberUtil memberUtil;
    private final ObjectMapper om;

    public void save(RequestAnswer form) {
        Member member = memberUtil.getMember();
        Long srvyNo = form.getSrvyNo();
        SurveyInfo surveyInfo = infoService.get(srvyNo);
        List<SurveyQuestion> qItems = surveyInfo.getQuestions();

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
                Long totScr = getScore(answers, qItems);
                surveyResult.setTotScr(totScr);

                /* 점수별 설문 결과 설명 */
                Map<String, String> result  = null;
                List<Map<String, String>> criteriaInfo = surveyInfo.get_criteriaInfo();
                for (Map<String, String> data : criteriaInfo) {
                    String[] range = data.get("range").split("_");
                    long rangeStart = Long.parseLong(range[0]);
                    long rangeEnd = Long.parseLong(range[1]);
                    if (totScr >= rangeStart && totScr <= rangeEnd) {
                        result = data;
                        break;
                    }
                }

                if (result != null) {
                    String resultDescription = om.writeValueAsString(result);
                    surveyResult.setResultDescription(resultDescription);
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        resultRepository.saveAndFlush(surveyResult);
    }

    public Long getScore(List<Map<Long, Integer>> answerData, List<SurveyQuestion> qItems) {
        long score = 0L;
        for (Map<Long, Integer> answer : answerData) {
            for (Map.Entry<Long, Integer> entry : answer.entrySet()) {
                Long itemNo = entry.getKey();
                Integer answerNo = entry.getValue();
                SurveyQuestion item = qItems.stream().filter(i -> i.getItemNo().equals(itemNo)).findFirst().orElse(null);
                if (item != null) {
                    List<Map<String, Object>> data = item.get_questions();
                    Map<String, Object> _data = data.get(answerNo);
                    long _score = (long)_data.get("score");
                    score += _score;
                }
            } // endfor
        } // endfor


        return score;
    }
}
