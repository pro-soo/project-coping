package com.project.health.repository;

import com.project.health.entity.RegionCode;
import com.querydsl.core.Tuple;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionCodeRepositoryCustom {
    List<RegionCode> findLowRegionInfo(@Param("znCd") String znCd);

    List<Tuple> findAllRegionInfo();
}
