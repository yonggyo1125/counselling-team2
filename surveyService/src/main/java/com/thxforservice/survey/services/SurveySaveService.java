package com.thxforservice.survey.services;

import com.thxforservice.member.MemberUtil;
import com.thxforservice.survey.controllers.RequestSurvey;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.exceptions.SurveyInfoNotFoundException;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

//설문지 등록, 수정
@Service
@RequiredArgsConstructor
public class SurveySaveService {

    private final HttpServletRequest request;
    private final SurveyInfoRepository surveyInfoRepository;
    private final MemberUtil memberUtil;

    public void save(RequestSurvey form) {

        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode.trim() : "write";
        System.out.println("form:" + form);
        SurveyInfo data = null;
        Long srvyNo = form.getSrvyNo();

        if (srvyNo != null && mode.equals("update")) { // 글 수정

            data = surveyInfoRepository.findById(srvyNo).orElseThrow(SurveyInfoNotFoundException::new);
            System.out.println("여기 : " + data);
        } else { // 글 작성
            String srvyNm = form.getSrvyNm();

            //km?


        }

        /* 설문 작성,  설문 수정 공통 S */

        data.setSrvyUse(form.getSrvyUse());
        data.setSrvyReqHr(form.getSrvyReqHr());
        data.setSrvyExpln(form.getSrvyExpln());

        /* 글 작성, 글 수정 공통 E */

        // 설문 저장 처리
        surveyInfoRepository.saveAndFlush(data);


    }
}
