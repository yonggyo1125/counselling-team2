package com.thxforservice.survey.exceptions;


import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class SurveyInfoNotFoundException extends CommonException {
    public SurveyInfoNotFoundException() {
        super("Not.Black", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }

}
