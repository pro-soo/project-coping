package com.project.health.repository;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.entity.DiseaseForecastInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseForeCastInfoRepository extends JpaRepository<DiseaseForecastInfo,String> {

    @Query(value = "select new com.project.health.dto.DiseaseForeCastInfoDto(d, r) from DiseaseForecastInfo d left join RegionCode r on d.znCd = r.znCd and d.lowrnkZnCd = r.lowrnkZnCd where d.dissCd = :dissCd and r.znCd=:znCd")
    List<DiseaseForeCastInfoDto> searchDissInfoList (@Param("dissCd") String dissCd, @Param("znCd") String znCd);
}
