package com.project.health.dto;


import com.project.health.entity.DiseaseCode;
import com.project.health.entity.DiseaseForecastInfo;
import com.project.health.entity.RiskGradeCode;
import com.project.health.entity.RegionCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@ToString
public class DiseaseForeCastInfoDto {

    private String lowrnkZnCdNm; //하위 지역코드명
    private String znCdNm; //지역코드명
    private String dt; //예측일자
    private String cnt; //질병 예측진료건수
    private String riskNm; //질병 예측위험도 명
    private String dissRiskXpln; //질병 위험도지침
    private String dissCdNm; //질병명

    public DiseaseForeCastInfoDto (DiseaseForecastInfo d, RegionCode r, DiseaseCode c, RiskGradeCode g){
        this.lowrnkZnCdNm = r.getLowrnkZnCdNm();
        this.znCdNm = r.getZnCdNm();
        this.dt = d.getDt();
        this.cnt = String.valueOf(d.getCnt());
        this.riskNm = g.getRiskNm();
        this.dissRiskXpln = d.getDissRiskXpln();
        this.dissCdNm = c.getDissCdNm();
    }
}
