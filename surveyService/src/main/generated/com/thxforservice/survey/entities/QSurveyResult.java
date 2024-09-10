package com.thxforservice.survey.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSurveyResult is a Querydsl query type for SurveyResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyResult extends EntityPathBase<SurveyResult> {

    private static final long serialVersionUID = 626042907L;

    public static final QSurveyResult surveyResult = new QSurveyResult("surveyResult");

    public QSurveyResult(String variable) {
        super(SurveyResult.class, forVariable(variable));
    }

    public QSurveyResult(Path<? extends SurveyResult> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyResult(PathMetadata metadata) {
        super(SurveyResult.class, metadata);
    }

}

