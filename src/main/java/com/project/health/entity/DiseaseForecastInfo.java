package com.project.health.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "DISEASE_FORCAST_INFO")
@Getter
@ToString
@NoArgsConstructor
public class DiseaseForecastInfo {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lowrnk_zn_cd")
    private RegionCode regionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diss_cd")
    private DiseaseCode diseaseCode;

    private String dt; //예측일자
    private int cnt; //질병 예측진료건수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risk")
    private RiskGradeCode riskGradeCode;

    @Builder
    public DiseaseForecastInfo(int id, String dissCd, String dt, String lowrnkZnCd, int cnt, int risk) {
        this.id = id;
        this.diseaseCode = DiseaseCode.builder().dissCd(dissCd).build();
        this.dt = dt;
        this.regionCode = RegionCode.builder().lowrnkZnCd(lowrnkZnCd).build();
        this.cnt = cnt;
        this.riskGradeCode = RiskGradeCode.builder().risk(risk).build();
    }
}
