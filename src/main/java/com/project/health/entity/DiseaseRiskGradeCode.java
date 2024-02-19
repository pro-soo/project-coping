package com.project.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DiseaseRiskGradeCode")
public class DiseaseRiskGradeCode {

    @Id
    @Column(name = "risk")
    private Long risk; //위험도
    private String riskNm; //위험도명

}
