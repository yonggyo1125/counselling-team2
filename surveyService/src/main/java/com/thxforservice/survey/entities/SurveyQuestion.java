package com.thxforservice.survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="SRVY_QITEM_INFO") //한 질문당
public class SurveyQuestion {
    @Id @GeneratedValue
    @Column(name="srvyQitemNo")
    private Long itemNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="srvyNo")
    private SurveyInfo surveyInfo;

    @Column(name="srvyQItemTTL")
    private String title; // 질문

    // 문항1....^^1점||문항2......^^2점||문항3...^^3점
    @Lob
    private String questions; // 문항, 문항의 점수 json


    @Transient
    private List<Map<String, Object>> _questions;

}