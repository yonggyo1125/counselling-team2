package com.thxforservice.survey.services;

import com.thxforservice.global.ListData;
import com.thxforservice.survey.controllers.AnswerSearch;
import com.thxforservice.survey.entities.SurveyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyResultInfoService {

    public SurveyResult get(Long prgrsNo) {
        return null;
    }

    public ListData<SurveyResult> getList(AnswerSearch search) {

        return null;
    }

    private void addInfo(SurveyResult item) {

    }
}
