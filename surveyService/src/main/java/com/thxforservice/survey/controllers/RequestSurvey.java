package com.thxforservice.survey.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestSurvey {

    private Long srvyNo;
    private String mode = "write"; // write :  작성, update -  수정

    @NotBlank
    private String srvyNm; // 검사 이름

    private Boolean srvyUse; // 검사 사용 여부


    private String srvyReqHr; // 검사 소요시간


    private String srvyExpln; // 검사 설명

}
