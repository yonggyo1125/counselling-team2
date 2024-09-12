package com.thxforservice.survey.controllers;

import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.constants.DeleteStatus;
import com.thxforservice.global.rests.JSONData;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyResult;
import com.thxforservice.survey.services.SurveyInfoService;
import com.thxforservice.survey.services.answer.AnswerDeleteService;
import com.thxforservice.survey.services.answer.AnswerInfoService;
import com.thxforservice.survey.services.answer.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Collections;
import java.util.List;

@Tag(name = "Survey", description = "설문 API")
@RestController
@RequiredArgsConstructor
public class SurveyController {


    private final AnswerInfoService answerInfoService;
    private final AnswerDeleteService answerDeleteService;
    private final SurveyInfoService surveyInfoService;
    private final Utils utils;
    private final AnswerService answerService;
    //km   private final SurveyConfigInfoService sConfigInfoService;

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
    public JSONData list(SurveySearch search, DeleteStatus status) {


        ListData<SurveyInfo> data = surveyInfoService.getList(search, status);

        return new JSONData(data);

//        try {
//            // 서비스에서 설문지 리스트 가져오기
//            List<SurveyInfo> surveys = sConfigInfoService.getList().orElse(Collections.emptyList());
//
//            // 성공적으로 데이터를 가져온 경우 JSONData 객체 생성
//            if (surveys.isEmpty()) {
//                return new JSONData(true, "Success", surveys);
//            } else {
//                // 데이터가 없는 경우
//                return new JSONData(true, "No surveys found", Collections.emptyList());
//            }
//        } catch (Exception e) {
//            // 예외 발생 시
//            e.printStackTrace();
//            return new JSONData(false, "An error occurred while retrieving surveys", null);
//        }


    }

    @PreAuthorize("hasRole('USER')") //km 추가?
    @Operation(summary = "설문지 하나 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "srvyNo", required = true, description = "경로변수, 설문지 등록번호")
    @GetMapping("/info/{srvyNo}")
    public JSONData info(@PathVariable("srvyNo") Long srvyNo) {
  SurveyInfoResponse response = surveyInfoService.get(srvyNo); //km?
        return new JSONData(response);
    }


    @Operation(summary = "답변 등록 처리", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<Void> answerProcess(@Valid RequestAnswer form, Errors errors, Model model, SessionStatus status, HttpSession session) {

        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors().stream().toString());
            return ResponseEntity.badRequest().build();
        }

        String mode = form.getMode();
        System.out.println(form);
        System.out.println(errors.getAllErrors().stream().toString());
        mode = mode != null && StringUtils.hasText(mode.trim()) ? mode.trim() : "write";

        answerService.process(form);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "답변한 설문지 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/answers")
    public JSONData myAnswers(@PathVariable("srvyNo") Long srvyNo, @ModelAttribute SurveySearch search, Model model, DeleteStatus status, SurveyResult prgrsNo) {
        //km   commonProcess(srvyNo, "answers", model);

        answerInfoService.getList(search, status, prgrsNo);
        return null;
        //km     return utils.tpl("mypage/myAnswers");
    }

    @Operation(summary = "답변 상세 내용", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "prgrsNo", required = true, description = "경로변수, 답변 등록 번호")
    @GetMapping("/answer/{prgrsNo}")
    public JSONData answer(@PathVariable("prgrsNo") Long prgrsNo) {
        answerInfoService.get(prgrsNo);
        return null;
    }

    @Operation(summary = "답변 삭제", method = "DELETE")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "prgrsNo", required = true, description = "경로변수, 답변 등록 번호")
    @DeleteMapping("/{prgrsNo}")
    public void delete(@PathVariable("prgrsNo") Long prgrsNo) {

        answerDeleteService.delete(prgrsNo);
    }
}