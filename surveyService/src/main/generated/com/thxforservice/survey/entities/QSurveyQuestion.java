package com.thxforservice.survey.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyQuestion is a Querydsl query type for SurveyQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyQuestion extends EntityPathBase<SurveyQuestion> {

    private static final long serialVersionUID = -498264988L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyQuestion surveyQuestion = new QSurveyQuestion("surveyQuestion");

    public final NumberPath<Long> itemNo = createNumber("itemNo", Long.class);

    public final StringPath questions = createString("questions");

    public final QSurveyInfo surveyInfo;

    public final StringPath title = createString("title");

    public QSurveyQuestion(String variable) {
        this(SurveyQuestion.class, forVariable(variable), INITS);
    }

    public QSurveyQuestion(Path<? extends SurveyQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurveyQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurveyQuestion(PathMetadata metadata, PathInits inits) {
        this(SurveyQuestion.class, metadata, inits);
    }

    public QSurveyQuestion(Class<? extends SurveyQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.surveyInfo = inits.isInitialized("surveyInfo") ? new QSurveyInfo(forProperty("surveyInfo")) : null;
    }

}

