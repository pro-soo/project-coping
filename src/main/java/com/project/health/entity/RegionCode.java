package com.project.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RegionCode")
public class RegionCode {

    @Id
    @Column(name = "zn_cd_id")
    private Long id;
    private int znCd; //상위 지역코드
    private String znCdNm; //지역명
    private int lowrnkZnCd; //시군구 지역코드
    private String lowrnkZnCdNm; //시군구명
}
