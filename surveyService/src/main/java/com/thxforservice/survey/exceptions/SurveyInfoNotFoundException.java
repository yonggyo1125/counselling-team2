package com.thxforservice.survey.exceptions;


import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class SurveyInfoNotFoundException extends CommonException {
    public SurveyInfoNotFoundException() {
        super("Survey info not found", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }

    public SurveyInfoNotFoundException(String message) {
        super(message);
    }
}
