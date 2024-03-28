package com.project.health.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRiskGradeCode is a Querydsl query type for RiskGradeCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRiskGradeCode extends EntityPathBase<RiskGradeCode> {

    private static final long serialVersionUID = -1520475526L;

    public static final QRiskGradeCode riskGradeCode = new QRiskGradeCode("riskGradeCode");

    public final NumberPath<Integer> risk = createNumber("risk", Integer.class);

    public final StringPath riskNm = createString("riskNm");

    public QRiskGradeCode(String variable) {
        super(RiskGradeCode.class, forVariable(variable));
    }

    public QRiskGradeCode(Path<? extends RiskGradeCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRiskGradeCode(PathMetadata metadata) {
        super(RiskGradeCode.class, metadata);
    }

}

