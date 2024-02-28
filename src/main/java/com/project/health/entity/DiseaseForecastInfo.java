package com.project.health.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "DISEASE_FORCAST_INFO")
@Getter
@ToString
public class DiseaseForecastInfo {

    @Id
    private int id;
//    private String lowrnkZnCd1; //하위 지역코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lowrnk_zn_cd")
    private RegionCode regionCode;

//    private String znCd; //지역코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diss_cd")
    private DiseaseCode diseaseCode;

    //    private String dissCd; //질병코드
    private String dt; //예측일자
    private int cnt; //질병 예측진료건수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risk")
    private RiskGradeCode riskGradeCode;
//    private int risk; //질병 예측위험도
    private String dissRiskXpln; //질병 위험도지침

    @Builder
    public DiseaseForecastInfo(int id,String dissCd, String dt, String znCd, String lowrnkZnCd, int cnt, int risk, String dissRiskXpln) {

        this.id = id;
        this.diseaseCode = DiseaseCode.builder().dissCd(dissCd).build();
        this.dt = dt;
        this.regionCode = RegionCode.builder().lowrnkZnCd(lowrnkZnCd).build();
        this.cnt = cnt;
        this.riskGradeCode = RiskGradeCode.builder().risk(risk).build();
        this.dissRiskXpln = dissRiskXpln;
    }

    public DiseaseForecastInfo() {

    }

}
