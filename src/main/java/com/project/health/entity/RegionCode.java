package com.project.health.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
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

    @Id
    @Column(name = "zn_cd_id")
    private Long id;
    private String znCd; //상위 지역코드
    private String znCdNm; //지역명
    private String lowrnkZnCd; //시군구 지역코드
    private String lowrnkZnCdNm; //시군구명

}
