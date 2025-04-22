package com.customer_service.customer_service.app.model.dbs;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portal_job_grade")
public class JobGradeModel {
    @Id
    private String jobGradeId;
    private String jobGradeNameEn;
    private String jobGradeNameTh;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String jobPositionId;
    private String corporateId;




}
