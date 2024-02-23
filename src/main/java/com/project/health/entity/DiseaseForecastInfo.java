package com.project.health.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DISEASE_FORCAST_INFO")
@Getter
@ToString
public class DiseaseForecastInfo {

    @Id
    private String lowrnkZnCd; //하위 지역코드
    private String znCd; //지역코드
    private String dissCd; //질병코드
    private String dt; //예측일자
    private int cnt; //질병 예측진료건수
    private int risk; //질병 예측위험도
    private String dissRiskXpln; //질병 위험도지침

    @Builder
    public DiseaseForecastInfo(String dissCd, String dt, String znCd, String lowrnkZnCd, int cnt, int risk, String dissRiskXpln) {
        this.dissCd = dissCd;
        this.dt = dt;
        this.znCd = znCd;
        this.lowrnkZnCd = lowrnkZnCd;
        this.cnt = cnt;
        this.risk = risk;
        this.dissRiskXpln = dissRiskXpln;
    }

    public DiseaseForecastInfo() {

    }

}
