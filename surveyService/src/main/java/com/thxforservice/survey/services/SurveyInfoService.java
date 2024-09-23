package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.thxforservice.global.CommonSearch;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import com.thxforservice.survey.entities.QSurveyInfo;
import com.thxforservice.survey.entities.QSurveyQuestion;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyQuestion;
import com.thxforservice.survey.exceptions.SurveyInfoNotFoundException;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import com.thxforservice.survey.repositories.SurveyQuestionRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Order.asc;

@Service
@RequiredArgsConstructor
public class SurveyInfoService {

    private final SurveyInfoRepository infoRepository;
    private final SurveyQuestionRepository questionRepository;
    private final HttpServletRequest request;
    private final ObjectMapper om;

    public SurveyInfo get(Long sNo) {

        SurveyInfo item = infoRepository.findById(sNo).orElseThrow(SurveyInfoNotFoundException::new);

        addInfo(item);

        return item;
    }

    public ListData<SurveyInfo> getList(CommonSearch search) {

        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit;

        QSurveyInfo surveyInfo = QSurveyInfo.surveyInfo;
        BooleanBuilder andBuilder = new BooleanBuilder();

        /* 검색 조건 처리 - 키워드 검색만 추가 S */
        String skey = search.getSkey();
        if (StringUtils.hasText(skey)) {
            andBuilder.and(surveyInfo.srvyNm.contains(skey.trim()));
        }
        /* 검색 조건 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt"));

        Page<SurveyInfo> data = infoRepository.findAll(andBuilder, pageable);
        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);

        List<SurveyInfo> items = data.getContent();

        return new ListData<>(items, pagination);
    }

    private void addInfo(SurveyInfo item) {
        try {
            String criteriaInfo = item.getCriteriaInfo();
            if (StringUtils.hasText(criteriaInfo)) {
                List<Map<String, String>> _criteriaInfo = om.readValue(criteriaInfo, new TypeReference<>() {
                });
                item.set_criteriaInfo(_criteriaInfo);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        /* 설문지 가져오기 S */
        QSurveyQuestion surveyQuestion = QSurveyQuestion.surveyQuestion;
        List<SurveyQuestion> qItems = (List<SurveyQuestion>)questionRepository.findAll(surveyQuestion.surveyInfo.srvyNo.eq(item.getSrvyNo()), Sort.by(asc("itemNo")));


        if (qItems != null && !qItems.isEmpty()) {
            for (SurveyQuestion qItem : qItems) {
                String[] questions = qItem.getQuestions().split("\\|\\|");
                List<Map<String, Object>> _questions = new ArrayList<>();
                for (String _qItem : questions) {
                    String[] _q = _qItem.split("\\^\\^");

                    Map<String, Object> data = new HashMap<>();
                    data.put("question", _q[0]);
                    data.put("score", Integer.parseInt(_q[1]));
                    _questions.add(data);
                }
                qItem.set_questions(_questions);
            }
        }

        item.setQuestions(qItems);
        /* 설문지 가져오기 E */
    }
}
