package com.thxforservice.survey.controllers;

import com.thxforservice.global.Utils;
import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    private final HttpServletRequest request;
    private final Utils utils;

    @Operation(summary = "설문지 등록/수정")
    @RequestMapping(method={RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Void> saveSurvey(@Valid @RequestBody RequestSurvey form, Errors errors) {

        String method = request.getMethod().toUpperCase();
        form.setMode(method.equals("PATCH") ? "update" : "write");


        HttpStatus status = method.equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).build();
    }


    @Operation(summary = "답변 삭제", method = "DELETE")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "prgrsNo", required = true, description = "경로변수, 답변 등록 번호")
    @DeleteMapping("/{prgrsNo}")
    public void delete(@PathVariable("prgrsNo") Long prgrsNo) {

    }

    @Operation(summary = "설문지 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public JSONData list() {


        // 결과를 JSONData로 변환하여 반환
        return null;
    }
}
