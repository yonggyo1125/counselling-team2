package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import com.thxforservice.survey.controllers.AnswerSearch;
import com.thxforservice.survey.entities.QSurveyResult;
import com.thxforservice.survey.entities.SurveyResult;
import com.thxforservice.survey.exceptions.SurveyResultNotFoundException;
import com.thxforservice.survey.repositories.SurveyResultRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SurveyResultInfoService {

    private final SurveyResultRepository resultRepository;
    private final ObjectMapper om;
    private final HttpServletRequest request;

    public SurveyResult get(Long prgrsNo) {

        SurveyResult item = resultRepository.findById(prgrsNo).orElseThrow(SurveyResultNotFoundException::new);

        addInfo(item);

        return item;
    }

    public ListData<SurveyResult> getList(AnswerSearch search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit;

        BooleanBuilder andBuilder = new BooleanBuilder();
        QSurveyResult surveyResult = QSurveyResult.surveyResult;
        List<String> email = search.getEmail();
        if (email != null && !email.isEmpty()) {
            andBuilder.and(surveyResult.email.in(email));
        }

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt"));
        Page<SurveyResult> data = resultRepository.findAll(andBuilder, pageable);
        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);

        List<SurveyResult> items = data.getContent();
        items.forEach(this::addInfo);

        return new ListData<>(items, pagination);
    }

    private void addInfo(SurveyResult item) {
        String answerData = item.getAnswerData();
        if (StringUtils.hasText(answerData)) {
            try {
                List<Map<Long, Integer>> answers = om.readValue(answerData, new TypeReference<>() {});
                item.setAnswers(answers);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
