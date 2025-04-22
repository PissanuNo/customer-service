package com.customer_service.customer_service.app.model.dbs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portal_job_position")
public class JobPositionModel {
    @Id
    private String jobPositionId;
    private String jobPositionNameEn;
    private String jobPositionNameTh;
    private Integer jobPositionLevel;
    private BigDecimal salaryRangMin;
    private BigDecimal salaryRangMax;
    private String corporateId;


}
