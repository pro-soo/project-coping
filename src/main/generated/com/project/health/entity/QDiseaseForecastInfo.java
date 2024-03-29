package com.project.health.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiseaseForecastInfo is a Querydsl query type for DiseaseForecastInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiseaseForecastInfo extends EntityPathBase<DiseaseForecastInfo> {

    private static final long serialVersionUID = -164495542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiseaseForecastInfo diseaseForecastInfo = new QDiseaseForecastInfo("diseaseForecastInfo");

    public final NumberPath<Integer> cnt = createNumber("cnt", Integer.class);

    public final QDiseaseCode diseaseCode;

    public final StringPath dt = createString("dt");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRegionCode regionCode;

    public final QRiskGradeCode riskGradeCode;

    public QDiseaseForecastInfo(String variable) {
        this(DiseaseForecastInfo.class, forVariable(variable), INITS);
    }

    public QDiseaseForecastInfo(Path<? extends DiseaseForecastInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiseaseForecastInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiseaseForecastInfo(PathMetadata metadata, PathInits inits) {
        this(DiseaseForecastInfo.class, metadata, inits);
    }

    public QDiseaseForecastInfo(Class<? extends DiseaseForecastInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diseaseCode = inits.isInitialized("diseaseCode") ? new QDiseaseCode(forProperty("diseaseCode")) : null;
        this.regionCode = inits.isInitialized("regionCode") ? new QRegionCode(forProperty("regionCode")) : null;
        this.riskGradeCode = inits.isInitialized("riskGradeCode") ? new QRiskGradeCode(forProperty("riskGradeCode")) : null;
    }

}

