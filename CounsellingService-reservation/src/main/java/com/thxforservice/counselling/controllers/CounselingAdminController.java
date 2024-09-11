package com.thxforservice.counselling.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="CounselingAdmin", description = "상담 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounselingAdminController {

    private final HttpServletRequest request;

    /**
     * 1. 집단 상담 프로그램 관리
     *     - 집단 상담 프로그램 추가, 수정, 삭제
     *         추가 - POST /group
     *         수정 - PATCH /group/update/{pgmSeq}
     *         삭제 - DELETE /group/{pgmSeq}
     *
     *     - 집단 상담 프로그램 목록 - GET /group
     *
     *     - 집단 상담 신청 목록 - GET /group/apply
     *     - 집단 상담 신청 하나 정보 - GET /group/apply/{pgmRegSeq}

     *
     * 2. 개별 상담 신청 관리
     *    - 개별 상담 신청 목록 - /apply
     *    - 개별 상담  신청 정보 - /apply/{cSeq}
     *
     *
     */

    @PostMapping("/group")
    public ResponseEntity<Void> register() {
        return save();
    }

    @PatchMapping("/group/update/{pgmSeq}")
    public ResponseEntity<Void> update(@PathVariable("pgmSeq") Long pgmSeq) {
        return save();
    }

    public ResponseEntity<Void> save() {


        HttpStatus status = request.getMethod().toUpperCase().equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;

        return ResponseEntity.status(status).build();
    }
}
