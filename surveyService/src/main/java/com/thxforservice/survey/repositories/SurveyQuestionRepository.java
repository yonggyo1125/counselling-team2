package com.thxforservice.survey.repositories;

import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> , QuerydslPredicateExecutor<SurveyQuestion> {

    List<SurveyQuestion> findBySurveyInfo(SurveyInfo surveyInfo);

    Optional<SurveyInfo> findBySrvyNo(Long srvyNo);

    List<SurveyQuestion> findBySurveyInfo_SrvyNo(Long srvyNo);



}
