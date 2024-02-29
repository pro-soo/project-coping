package com.project.health.repository;

import com.project.health.entity.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegionCodeRepository extends JpaRepository<RegionCode, String>, RegionCodeRepositoryCustom {
//  [@Query와 JPQL 사용하기]
//    /***
//     * 지역정보 조회
//     * @param znCdNm
//     * @return
//     */
//    @Query("select r from RegionCode r where r.znCdNm = :znCdNm order by r.lowrnkZnCd")
//    List<RegionCode> findAllRegionInfo(@Param("znCdNm") String znCdNm);
}
