package com.thxforservice.reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReserEnd is a Querydsl query type for ReserEnd
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserEnd extends EntityPathBase<ReserEnd> {

    private static final long serialVersionUID = -1284401842L;

    public static final QReserEnd reserEnd = new QReserEnd("reserEnd");

    public final NumberPath<Long> cSeq = createNumber("cSeq", Long.class);

    public QReserEnd(String variable) {
        super(ReserEnd.class, forVariable(variable));
    }

    public QReserEnd(Path<? extends ReserEnd> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReserEnd(PathMetadata metadata) {
        super(ReserEnd.class, metadata);
    }

}

