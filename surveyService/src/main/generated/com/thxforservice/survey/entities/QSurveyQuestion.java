package com.thxforservice.survey.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSurveyQuestion is a Querydsl query type for SurveyQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyQuestion extends EntityPathBase<SurveyQuestion> {

    private static final long serialVersionUID = -498264988L;

    public static final QSurveyQuestion surveyQuestion = new QSurveyQuestion("surveyQuestion");

    public QSurveyQuestion(String variable) {
        super(SurveyQuestion.class, forVariable(variable));
    }

    public QSurveyQuestion(Path<? extends SurveyQuestion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyQuestion(PathMetadata metadata) {
        super(SurveyQuestion.class, metadata);
    }

}

