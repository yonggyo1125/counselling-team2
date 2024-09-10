package com.thxforservice.survey.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSurveyInfo is a Querydsl query type for SurveyInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyInfo extends EntityPathBase<SurveyInfo> {

    private static final long serialVersionUID = -406311892L;

    public static final QSurveyInfo surveyInfo = new QSurveyInfo("surveyInfo");

    public QSurveyInfo(String variable) {
        super(SurveyInfo.class, forVariable(variable));
    }

    public QSurveyInfo(Path<? extends SurveyInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyInfo(PathMetadata metadata) {
        super(SurveyInfo.class, metadata);
    }

}

