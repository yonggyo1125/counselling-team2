package com.thxforservice.survey.controllers;

import com.thxforservice.global.CommonSearch;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.services.SurveyInfoService;
import com.thxforservice.survey.services.SurveyResultInfoService;
import com.thxforservice.survey.services.SurveyResultSaveService;
import com.thxforservice.survey.services.SurveySaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Survey", description = "설문 API")
@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyInfoService surveyInfoService;
    private final SurveySaveService surveySaveService;
    private final SurveyResultInfoService resultInfoService;
    private final SurveyResultSaveService resultSaveService;
    private final Utils utils;



    /**
     * 1. 설문지 목록 조회  GET - /
     * - 모든 회원이 열람할 수 있는 목록
     * <p>
     * 2. 설문지 하나 조회 (답변 페이지) - GET /info/{srvyNo}
     * 3. 설문지 답변 등록 처리 - POST - /
     * <p>
     * 마이페이지
     * 4. 답변한 설문지 목록
     * GET - /answers
     * <p>
     * 5. 답변 상세 내용
     * GET - /answer/{prgrsNo}
     */

    @Operation(summary = "설문지 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public JSONData list(CommonSearch search) {

        ListData<SurveyInfo> data = surveyInfoService.getList(search);

        return new JSONData(data);
    }

    @Operation(summary = "설문지 하나 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "srvyNo", required = true, description = "경로변수, 설문지 등록번호")
    @GetMapping("/info/{srvyNo}")
    public JSONData info(@PathVariable("srvyNo") Long srvyNo) {

        SurveyInfo data = surveyInfoService.get(srvyNo);

        return new JSONData(data);
    }


    @Operation(summary = "답변 등록 처리", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<Void> answerProcess(@Valid RequestAnswer form, Errors errors, RequestAnswer surveyResult) {

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        resultSaveService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




    @Operation(summary = "답변한 설문지 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/answers")
    public JSONData myAnswers(@ModelAttribute CommonSearch search, Model model) {
        return null;
    }

    @Operation(summary = "답변 상세 내용", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "prgrsNo", required = true, description = "경로변수, 답변 등록 번호")
    @GetMapping("/answer/{prgrsNo}")
    public JSONData answer(@PathVariable("prgrsNo") Long prgrsNo) {

       return null;
    }


}