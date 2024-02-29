package com.project.health.repository;

import com.project.health.entity.RegionCode;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionCodeRepositoryCustom {
    List<RegionCode> findAllRegionInfo(@Param("znCdNm") String znCdNm);
}
