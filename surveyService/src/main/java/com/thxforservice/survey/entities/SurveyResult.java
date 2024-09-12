package com.thxforservice.survey.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="SRVY_ANS_RSLT")
public class SurveyResult extends BaseMemberEntity {

    @Id @GeneratedValue
    @Column(name="srvyPrgrsNo")
    private Long prgrsNo; // SurveyResult

    @JoinColumn(name="srvyNo")
    @ManyToOne(fetch=FetchType.LAZY)
    private SurveyInfo surveyInfo;

    private Long studentNo;

    @Column(length=80, nullable = false)
    private String email; // 로그인 회원 이메일

    @Column(length=40, nullable = false)
    private String username; // 로그인 회원명

    @Lob
    private String answerData; // 학생이 답한 질문 데이터 json?

    private Long totScr; // 총 점수


}
