package com.project.health.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REGION_CODE")
@Getter
@ToString
public class RegionCode {


    private String znCd; //상위 지역코드
    private String znCdNm; //지역명
    @Id
    @Column(name = "lowrnk_zn_cd")
    private String lowrnkZnCd; //시군구 지역코드
    private String lowrnkZnCdNm; //시군구명

    @Builder
    public RegionCode(String lowrnkZnCd) {
        this.lowrnkZnCd = lowrnkZnCd;
    }

    public RegionCode() {

    }
}
