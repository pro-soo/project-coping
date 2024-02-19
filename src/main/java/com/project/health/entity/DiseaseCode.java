package com.project.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DiseaseCode")
public class DiseaseCode {

    @Id
    @Column(name = "diss_cd")
    private Long dissCd; //질병코드
    private String dissCdNm; //질병명

}
