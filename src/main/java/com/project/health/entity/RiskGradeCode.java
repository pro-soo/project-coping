package com.project.health.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RISK_GRADE_CODE")
@Getter
@NoArgsConstructor
public class RiskGradeCode {

    @Id
    private int risk; //위험도
    private String riskNm; //위험도명

    @Builder
    public RiskGradeCode(int risk) {
        this.risk = risk;
    }
}
