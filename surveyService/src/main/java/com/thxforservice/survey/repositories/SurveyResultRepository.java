package com.thxforservice.survey.repositories;

import com.thxforservice.survey.entities.SurveyQuestion;
import com.thxforservice.survey.entities.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> , QuerydslPredicateExecutor<SurveyResult> {

    List<SurveyResult> findBySurveyInfoSrvyNo(Long srvyNo);
}
