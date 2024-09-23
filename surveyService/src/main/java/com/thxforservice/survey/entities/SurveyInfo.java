package com.thxforservice.survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thxforservice.global.entities.BaseMemberEntity;
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
@Table(name="SELF_ASMT_SRVY_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyInfo extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long srvyNo;

    @Column(length = 45)
    private String gid;

    @Column(nullable = false)
    private String srvyNm; // 검사 이름

    private Boolean srvyUse; // 검사 사용 여부

    @Column(length=20)
    private String srvyReqHr; // 검사 소요시간

    @Lob
    private String srvyExpln; // 검사 설명

    @Lob
    private String criteriaInfo; // 기준 시작, 종료 점수, 결과 내용 json

    @Transient
    public List<SurveyQuestion> questions;

    @Transient
    public List<Map<String, String>> _criteriaInfo;
}
