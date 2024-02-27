package com.project.health.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISEASE_CODE")
@Getter
public class DiseaseCode {

    @Id
    @Column(name = "diss_cd")
    private String dissCd; //질병코드
    private String dissCdNm; //질병명

    @Builder
    public DiseaseCode(String dissCd) {
        this.dissCd = dissCd;
    }

    public DiseaseCode() {

    }
}
