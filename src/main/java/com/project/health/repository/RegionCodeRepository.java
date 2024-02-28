package com.project.health.repository;

import com.project.health.dto.RegionCodeDto;
import com.project.health.entity.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RegionCodeRepository extends JpaRepository<RegionCode, String> {

    /***
     * 지역정보 조회
     * @param znCdNm
     * @return
     */
    @Query("select r from RegionCode r where r.znCdNm = :znCdNm order by r.lowrnkZnCd")
    List<RegionCode> findAllRegionInfo(@Param("znCdNm") String znCdNm);
}
