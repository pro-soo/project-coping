package com.project.health.repository;

import com.project.health.dto.RegionCodeDto;
import com.project.health.entity.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RegionCodeRepository extends JpaRepository<RegionCode, String> {

    @Query("select r from RegionCode r where r.znCd = :znCd order by r.lowrnkZnCd")
    List<RegionCode> findAllRegionInfo(@Param("znCd") String znCd);
}
