package com.thxforservice.survey.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="SurveyAdmin", description = "설문 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class SurveyAdminController {

    private final HttpServletRequest request;

    @Operation(summary = "설문지 등록")
    @PostMapping
    public ResponseEntity<Void> registerSurvey() {

        return saveSurvey();
    }

    @Operation(summary = "설문지 수정")
    @PatchMapping
    public ResponseEntity<Void> updateSurvey(@PathVariable("srvyNo") Long sNo) {

        return saveSurvey();
    }

    public ResponseEntity<Void> saveSurvey() {


        HttpStatus status = request.getMethod().toUpperCase().equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).build();
    }
}
