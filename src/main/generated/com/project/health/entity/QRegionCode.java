package com.project.health.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRegionCode is a Querydsl query type for RegionCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRegionCode extends EntityPathBase<RegionCode> {

    private static final long serialVersionUID = -1791159556L;

    public static final QRegionCode regionCode = new QRegionCode("regionCode");

    public final StringPath lowrnkZnCd = createString("lowrnkZnCd");

    public final StringPath lowrnkZnCdNm = createString("lowrnkZnCdNm");

    public final StringPath znCd = createString("znCd");

    public final StringPath znCdNm = createString("znCdNm");

    public QRegionCode(String variable) {
        super(RegionCode.class, forVariable(variable));
    }

    public QRegionCode(Path<? extends RegionCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegionCode(PathMetadata metadata) {
        super(RegionCode.class, metadata);
    }

}

