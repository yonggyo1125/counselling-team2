package com.thxforservice.survey.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class SurveyResultNotFoundException  extends CommonException {
    public SurveyResultNotFoundException(){
        super("Survey Result not found", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
