package com.thxforservice.survey.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyResult is a Querydsl query type for SurveyResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyResult extends EntityPathBase<SurveyResult> {

    private static final long serialVersionUID = 626042907L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyResult surveyResult = new QSurveyResult("surveyResult");

    public final com.thxforservice.global.entities.QBaseEntity _super = new com.thxforservice.global.entities.QBaseEntity(this);

    public final StringPath answerData = createString("answerData");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> prgrsNo = createNumber("prgrsNo", Long.class);

    public final StringPath resultDescription = createString("resultDescription");

    public final QSurveyInfo surveyInfo;

    public final NumberPath<Long> totScr = createNumber("totScr", Long.class);

    public final StringPath username = createString("username");

    public QSurveyResult(String variable) {
        this(SurveyResult.class, forVariable(variable), INITS);
    }

    public QSurveyResult(Path<? extends SurveyResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurveyResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurveyResult(PathMetadata metadata, PathInits inits) {
        this(SurveyResult.class, metadata, inits);
    }

    public QSurveyResult(Class<? extends SurveyResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.surveyInfo = inits.isInitialized("surveyInfo") ? new QSurveyInfo(forProperty("surveyInfo")) : null;
    }

}

