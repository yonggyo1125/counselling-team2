package com.thxforservice.counseling.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 상담 신청 기본 - 그룹 상담
 *
 */
@Data
public class RequestGroupCounselingApply {

    @NotBlank
    private Long pgmSeq; //프로그램 id

    @NotBlank
    private Long studentNo; // 학번

    @NotBlank
    private String username; // 이름

    @NotBlank
    private String grade; // 학년

    @NotBlank
    private String department; // 학과

    private String email;
    private String mobile;
}
