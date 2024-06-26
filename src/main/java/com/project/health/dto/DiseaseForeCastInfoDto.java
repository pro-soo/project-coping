package com.project.health.dto;


import com.project.health.entity.*;
import com.querydsl.core.Tuple;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Slf4j
@ToString
public class DiseaseForeCastInfoDto {

    private String lowrnkZnCdNm; //하위 지역코드명
    private String znCdNm; //지역코드명
    private String dt; //예측일자
    private String cnt; //질병 예측진료건수
    private String riskNm; //질병 예측위험도 명
    private String dissCdNm; //질병명

    @Builder
    public DiseaseForeCastInfoDto(String lowrnkZnCdNm, String znCdNm, String dt, String cnt, String riskNm, String dissCdNm) {
        this.lowrnkZnCdNm = lowrnkZnCdNm;
        this.znCdNm = znCdNm;
        this.dt = dt;
        this.cnt = cnt;
        this.riskNm = riskNm;
        this.dissCdNm = dissCdNm;
    }

    public static DiseaseForeCastInfoDto from(DiseaseForecastInfo d) {
        RegionCode regionCode = d.getRegionCode();
        RiskGradeCode riskGradeCode = d.getRiskGradeCode();
        DiseaseCode diseaseCode = d.getDiseaseCode();

        return DiseaseForeCastInfoDto.builder()
                .lowrnkZnCdNm(regionCode.getLowrnkZnCdNm())
                .znCdNm(regionCode.getZnCdNm())
                .dt(d.getDt())
                .cnt(String.valueOf(d.getCnt()))
                .riskNm(riskGradeCode.getRiskNm())
                .dissCdNm(diseaseCode.getDissCdNm())
                .build();
    }

    public static List<DiseaseForeCastInfoDto> fromList(List<Tuple> infoList){
        List<DiseaseForeCastInfoDto> newList = new ArrayList<>();

        for (Tuple tuple : infoList){
            newList.add(DiseaseForeCastInfoDto.from(Objects.requireNonNull(tuple.get(QDiseaseForecastInfo.diseaseForecastInfo))));
        }

        return  newList;
    }
}
