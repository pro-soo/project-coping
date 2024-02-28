package com.project.health.repository;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.entity.DiseaseForecastInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseForeCastInfoRepository extends JpaRepository<DiseaseForecastInfo,String>, DiseaseForeCastInfoRepositoryCustom {

//    /***
//     *  질병예상정보 리스트 호출
//     * @param dissCd
//     * @param znCd
//     * @param dt
//     * @return
//     */
//    @Query(value = "select new com.project.health.dto.DiseaseForeCastInfoDto(a, b, c, d) " +
//            "from DiseaseForecastInfo a " +
//            "left join RegionCode b on a.regionCode.lowrnkZnCd = b.lowrnkZnCd " +
//            "left join DiseaseCode c on a.diseaseCode.dissCd = c.dissCd " +
//            "left join RiskGradeCode d on a.riskGradeCode.risk = d.risk " +
//            "where a.diseaseCode.dissCd = :dissCd " +
//            "and a.dt = :dt " +
//            "and b.znCd=:znCd")
//    List<DiseaseForeCastInfoDto> searchDissInfoList (@Param("dissCd") String dissCd, @Param("znCd") String znCd, @Param("dt") String dt);
//
//    /***
//     * 질병예상정보 전체 건수
//     * @return
//     */
//    @Query(value = "select count(d) from DiseaseForecastInfo d")
//    int allCountDissInfoList();
//
//    /***
//     * 질병예상정보 중복 조회
//     * @param dissCd
//     * @param znCd
//     * @return
//     */
//    @Query(value = "select count(d) from DiseaseForecastInfo d where d.diseaseCode.dissCd = :dissCd and d.regionCode.znCd = :znCd")
//    int duplCountDissInfo(@Param("dissCd") String dissCd, @Param("znCd") String znCd);
}
