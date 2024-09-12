//package com.thxforservice.survey.controllers;
//
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.thxforservice.global.Utils;
//import com.thxforservice.global.rests.JSONData;
//import com.thxforservice.survey.entities.SurveyInfo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class SurveyConfigInfoService {
//    private final RestTemplate restTemplate;
//    private final ObjectMapper om;
//    private final Utils utils;
//
//    public Optional <List<SurveyInfo>> getList() {
//        try {
//       String surveyUrl = utils.surveyUrl("/api/survey/list/");     //km?
//            ResponseEntity<JSONData> response = restTemplate.getForEntity(surveyUrl, JSONData.class);
//
//
//            // 상태 코드가 OK인지 확인
//            if (response.getStatusCode().is2xxSuccessful()) {
//                JSONData jsonData = response.getBody();
//
//                // 응답이 성공적인지 확인
//                if (jsonData != null && jsonData.isSuccess()) {
//                    Object data = jsonData.getData();
//                    if (data instanceof List<?>) {
//                        // JSON 데이터를 List<SurveyInfo>로 변환
//                        List<SurveyInfo> surveyList = om.readValue(om.writeValueAsString(data), new TypeReference<List<SurveyInfo>>() {});
//                        return Optional.of(surveyList); // 비어있는 리스트를 반환
//                    }
//                }
//            }
//        } catch (Exception e) {
//            // 예외 발생 시 로그 출력
//            e.printStackTrace();
//        }
//
//        // 실패 또는 예외 발생 시 빈 리스트 반환
//        return Optional.of(Collections.emptyList());
//    }
//}
