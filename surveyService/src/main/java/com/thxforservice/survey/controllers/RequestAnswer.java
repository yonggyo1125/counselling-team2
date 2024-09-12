package com.thxforservice.survey.controllers;

import com.thxforservice.survey.entities.SurveyInfo;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class RequestAnswer {


    private Long prgrsNo;
    private String mode = "write"; // write :  작성, update -  수정
    private Long studentNo;

    private String email; // 로그인 회원 이메일

    private String username; // 로그인 회원명

    private String answerData; // 학생이 답한 질문 데이터, 각 문항점수는 json으로 해서 총점계산

    private Long totScr; // 총 점수

    private Long srvyNo; // 추가: 설문 ID
}
