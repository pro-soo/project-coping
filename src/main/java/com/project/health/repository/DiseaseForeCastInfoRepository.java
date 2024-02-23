package com.project.health.repository;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.entity.DiseaseForecastInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DiseaseForeCastInfoRepository extends JpaRepository<DiseaseForecastInfo,String> {

    @Query(value = "select d from DiseaseForecastInfo d where d.dissCd = :dissCd")
    List<DiseaseForecastInfo> searchDissInfoList (@Param("dissCd") String dissCd);
}
