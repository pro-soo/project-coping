package com.project.health.repository;

import com.querydsl.core.Tuple;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiseaseForeCastInfoRepositoryCustom {

    List<Tuple> searchDissInfoList(@Param("dissCd") String dissCd, @Param("znCd") String znCd, @Param("dt") String dt);

    int allCountDissInfoList();

    int duplCountDissInfo(@Param("dissCd") String dissCd, @Param("znCd") String znCd);
}
