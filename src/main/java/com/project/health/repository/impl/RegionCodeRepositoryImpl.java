package com.project.health.repository.impl;

import com.project.health.entity.QRegionCode;
import com.project.health.entity.RegionCode;
import com.project.health.repository.RegionCodeRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class RegionCodeRepositoryImpl implements RegionCodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RegionCode> findLowRegionInfo(String znCd) {
        return queryFactory.selectFrom(QRegionCode.regionCode)
                .where(QRegionCode.regionCode.znCd.eq(znCd)).fetch();
    }

    @Override
    public List<Tuple> findAllRegionInfo() {
        return queryFactory.selectDistinct(QRegionCode.regionCode.znCd, QRegionCode.regionCode.znCdNm)
                .from(QRegionCode.regionCode)
                .orderBy(QRegionCode.regionCode.znCd.asc()).fetch();
    }
}
