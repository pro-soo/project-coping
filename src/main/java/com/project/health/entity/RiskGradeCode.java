package com.project.health.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RISK_GRADE_CODE")
@Getter
public class RiskGradeCode {

    @Id
    @Column(name = "risk")
    private int risk; //위험도
    private String riskNm; //위험도명


    @Builder
    public RiskGradeCode(int risk) {
        this.risk = risk;
    }

    public RiskGradeCode() {

    }
}
