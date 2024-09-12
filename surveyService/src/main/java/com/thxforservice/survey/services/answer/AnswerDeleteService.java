package com.thxforservice.survey.services.answer;

import com.thxforservice.file.services.FileDeleteService;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyResult;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import com.thxforservice.survey.repositories.SurveyResultRepository;
import com.thxforservice.survey.services.SurveyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerDeleteService {

    private final AnswerInfoService answerInfoService;
    private final SurveyResultRepository resultRepository;



    public void delete(Long prgrsNo) {
        SurveyResult data = answerInfoService.get(prgrsNo);
        data.setDeletedAt(LocalDateTime.now());

        resultRepository.saveAndFlush(data);
    }

    @Transactional
    public SurveyInfo complete(Long prgrsNo) {
        SurveyResult data = answerInfoService.get(prgrsNo);

        // 게시글 삭제
        resultRepository.delete(data);
        resultRepository.flush();
        return data.getSurveyInfo(); //km?
    }
}
