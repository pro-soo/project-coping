package com.project.health.repository;

import com.project.health.entity.DiseaseForecastInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseForeCastInfoRepository extends JpaRepository<DiseaseForecastInfo, String>, DiseaseForeCastInfoRepositoryCustom {

}
