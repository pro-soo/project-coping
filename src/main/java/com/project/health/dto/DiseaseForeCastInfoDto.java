package com.project.health.dto;


import com.project.health.entity.DiseaseForecastInfo;
import com.project.health.entity.RegionCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Builder
@Slf4j
@ToString
public class DiseaseForeCastInfoDto {

    private String lowrnkZnCdNm; //하위 지역코드명
    private String znCdNm; //지역코드명
    private String dissCd; //질병코드
    private String dt; //예측일자
    private String cnt; //질병 예측진료건수
    private String risk; //질병 예측위험도
    private String dissRiskXpln; //질병 위험도지침

    public static DiseaseForeCastInfoDto from (DiseaseForecastInfo diseaseForecastInfo){
        log.debug(" ======== DiseaseForeCastInfoDto ======= ");
        if(diseaseForecastInfo == null) return null;
        return DiseaseForeCastInfoDto.builder()
                .lowrnkZnCdNm("")
                .znCdNm("")
                .dissCd(diseaseForecastInfo.getDissCd())
                .dt(diseaseForecastInfo.getDt())
                .cnt(String.valueOf(diseaseForecastInfo.getCnt()))
                .risk(String.valueOf(diseaseForecastInfo.getRisk()))
                .dissRiskXpln(diseaseForecastInfo.getDissRiskXpln()).build();
    }
}
