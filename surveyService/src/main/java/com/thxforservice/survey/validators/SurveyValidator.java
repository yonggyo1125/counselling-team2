package com.thxforservice.survey.validators;

import com.thxforservice.survey.controllers.RequestSurvey;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SurveyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestSurvey.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        RequestSurvey form = (RequestSurvey)target;

        Long srvyNo = form.getSrvyNo();
        String mode = form.getMode();

        mode = StringUtils.hasText(mode) ? mode : "write";
        if (mode.equals("update") && (srvyNo == null || srvyNo < 1L)) {
            errors.rejectValue("srvNo", "NotBlank");
        }
    }
}
