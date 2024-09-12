package com.thxforservice.survey.controllers;

import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.survey.validators.SurveyValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Tag(name="SurveyAdmin", description = "설문 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class SurveyAdminController {

    private final SurveyValidator surveyValidator;
    private final HttpServletRequest request;
    private final Utils utils;

    @Operation(summary = "설문지 등록/수정")
    @RequestMapping(method={RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Void> saveSurvey(@Valid @RequestBody RequestSurvey form, Errors errors) {

        String method = request.getMethod().toUpperCase();

        surveyValidator.validate(form, errors);

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        // 추가, 수정 처리

        HttpStatus status = method.equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).build();
    }




}
