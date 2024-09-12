package com.thxforservice.survey.services;

import com.thxforservice.file.services.FileDeleteService;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyDeleteService {

    private final SurveyInfoService surveyInfoService;
private final SurveyInfoRepository surveyInfoRepository;
    private final FileDeleteService fileDeleteService;
    
    public void delete(Long srvyNo){
        /*
SurveyInfo data = surveyInfoService.get(srvyNo);
data.setDeletedAt(LocalDateTime.now());

surveyInfoRepository.saveAndFlush(data);

         */
    }

    @Transactional
    public SurveyInfo complete(Long srvyNo) {
        /*
        SurveyInfo data = surveyInfoService.get(srvyNo);

        // 게시글 삭제
        surveyInfoRepository.delete(data);
        surveyInfoRepository.flush();

        return data; */
        return null;
    }
}
