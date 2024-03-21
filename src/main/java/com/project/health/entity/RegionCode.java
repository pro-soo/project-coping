package com.project.health.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REGION_CODE")
@Getter
@ToString
@NoArgsConstructor
public class RegionCode {

    @Id
    private String lowrnkZnCd; //시군구 지역코드
    private String znCd; //상위 지역코드
    private String znCdNm; //지역명
    private String lowrnkZnCdNm; //시군구명

    @Builder
    public RegionCode(String lowrnkZnCd) {
        this.lowrnkZnCd = lowrnkZnCd;
    }
}
