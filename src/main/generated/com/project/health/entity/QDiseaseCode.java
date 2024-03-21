package com.project.health.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiseaseCode is a Querydsl query type for DiseaseCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiseaseCode extends EntityPathBase<DiseaseCode> {

    private static final long serialVersionUID = 947261678L;

    public static final QDiseaseCode diseaseCode = new QDiseaseCode("diseaseCode");

    public final StringPath dissCd = createString("dissCd");

    public final StringPath dissCdNm = createString("dissCdNm");

    public QDiseaseCode(String variable) {
        super(DiseaseCode.class, forVariable(variable));
    }

    public QDiseaseCode(Path<? extends DiseaseCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiseaseCode(PathMetadata metadata) {
        super(DiseaseCode.class, metadata);
    }

}

