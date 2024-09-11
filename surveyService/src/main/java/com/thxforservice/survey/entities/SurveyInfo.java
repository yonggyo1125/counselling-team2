package com.thxforservice.survey.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import jakarta.persistence.*;

@Entity
@Table(name="SELF_ASMT_SRVY_INFO")
public class SurveyInfo extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long srvyNo;

    @Column(nullable = false)
    private String srvyNm; // 검사 이름

    private Boolean srvyUse; // 검사 사용 여부

    @Column(length=20)
    private String srvyReqHr; // 검사 소요시간

    @Lob
    private String srvyExpln; // 검사 설명

    @Lob
    private String criteriaInfo; // 기준 시작, 종료 점수, 결과 내용
}
