package com.project.health.repository.impl;

import com.project.health.repository.DiseaseForeCastInfoRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.health.entity.QDiseaseCode.diseaseCode;
import static com.project.health.entity.QDiseaseForecastInfo.diseaseForecastInfo;
import static com.project.health.entity.QRegionCode.regionCode;
import static com.project.health.entity.QRiskGradeCode.riskGradeCode;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DiseaseForeCastInfoRepositoryImpl implements DiseaseForeCastInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tuple> searchDissInfoList(String dissCd, String znCd, String dt) {
//        log.debug("searchDissInfoList :: "+dissCd);
        return queryFactory
                .select(diseaseForecastInfo, regionCode, diseaseCode, riskGradeCode)
                .from(diseaseForecastInfo)
                .leftJoin(diseaseForecastInfo.regionCode, regionCode)
                .on(diseaseForecastInfo.regionCode.lowrnkZnCd.eq(regionCode.lowrnkZnCd))
                .leftJoin(diseaseForecastInfo.diseaseCode, diseaseCode)
                .on(diseaseForecastInfo.diseaseCode.dissCd.eq(diseaseCode.dissCd))
                .leftJoin(diseaseForecastInfo.riskGradeCode, riskGradeCode)
                .on(diseaseForecastInfo.riskGradeCode.risk.eq(riskGradeCode.risk))
                .where(diseaseForecastInfo.diseaseCode.dissCd.eq(dissCd)
                        .and(diseaseForecastInfo.dt.eq(dt)
                                .and(regionCode.znCd.eq(znCd)))).fetch();
    }

    @Override
    public int allCountDissInfoList() {
        return queryFactory
                .select(diseaseForecastInfo)
                .from(diseaseForecastInfo)
                .fetch().size();
    }

    @Override
    public int duplCountDissInfo(String dissCd, String znCd) {
        return queryFactory
                .select(diseaseForecastInfo)
                .from(diseaseForecastInfo)
                .where(diseaseForecastInfo.diseaseCode.dissCd.eq(dissCd)
                        .and(diseaseForecastInfo.regionCode.znCd.eq(znCd)))
                .fetch().size();
    }
}
