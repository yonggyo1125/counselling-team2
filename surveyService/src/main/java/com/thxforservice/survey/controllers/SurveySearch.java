package com.thxforservice.survey.controllers;

import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.util.List;

@Data
public class SurveySearch extends CommonSearch {

    private int limit;

    private Long srvyNo; //
    private List<Long> srvyNos; // 설문  여러개

    //km private List<String> category; // 분류 검색

    private String sort; // 정렬 조건

   // private Long num1;
}
