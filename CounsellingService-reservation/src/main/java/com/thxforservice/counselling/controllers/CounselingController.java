package com.thxforservice.counselling.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Counseling", description = "상담 API")
@RestController
@RequiredArgsConstructor
public class CounselingController {
    /**
     *  1. 개인 상담 신청  - POST /apply
     *  2. 집단 상담 하나 정보  - GET /group/info/{pgmSeq}
     *      - 집단 상담 정보(GroupCounseling)
     *          - 그룹 상담 스케줄 목록(GroupSchedule)
     *  3. 집단 상담 목록
     *      - GET /group :
     *
     *  4. 집단 상담 신청 처리
     *          - 신청시 연락처, 이메일은 변경 가능
     *          - POST /group/apply
     *              - 집단 상픔 스케줄 등록 번호(GroupSchedule)
     *              - 로그인한 회원의 학번
     *              - 입력한 email, mobile이 필요
     *              - 신청 가능 여부 체크 필요
     *
     *  5. 개인 상담 일정 변경
     *      - 편성된 상담은 empNo로 조회된 상담
     *      - 편성된 상담 일정 목록  GET /cs/list
     *      - 편성된 상담 하나 조회  GET /cs/info/{cSeq}
     *      - 편성된 상담 변경 처리  PATCH /cs/change
     *
     *      - 상담사 : 상담사로 권한 제한
     *      - rDate, rTime
     */
    @Operation(summary = "개인 상담 신청", method="POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/apply")
    public ResponseEntity<Void> apply() {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
