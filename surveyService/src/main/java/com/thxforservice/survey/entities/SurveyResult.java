package com.thxforservice.survey.entities;

import com.thxforservice.global.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name="SRVY_ANS_RSLT")
public class SurveyResult extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="srvyPrgrsNo")
    private Long prgrsNo; // SurveyResult

    @JoinColumn(name="srvyNo")
    @ManyToOne(fetch=FetchType.LAZY)
    private SurveyInfo surveyInfo;

    @Column(length=80, nullable = false)
    private String email;

    @Column(length=40, nullable = false)
    private String username; // 로그인 회원명

    @Lob
    private String answerData; // 학생이 답한 질문 데이터 json?

    private Long totScr; // 총 점수

    @Transient
    public List<Map<Long, Integer>> _answerData;
}
