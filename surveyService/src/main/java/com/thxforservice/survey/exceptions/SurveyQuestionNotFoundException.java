package com.thxforservice.survey.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class SurveyQuestionNotFoundException  extends CommonException {

    public SurveyQuestionNotFoundException(String message) {
        super(message);
    }
}
