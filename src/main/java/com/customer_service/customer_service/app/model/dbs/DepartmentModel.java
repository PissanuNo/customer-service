package com.customer_service.customer_service.app.model.dbs;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portal_department")
public class DepartmentModel {
    @Id
    private String departmentId;
    private String departmentNameEn;
    private String departmentNameTh;
    private String corporateId;
    private Integer hierarchyLevel;
    private String parentDepartmentId;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createDate =  new Timestamp(System.currentTimeMillis());

    @Builder.Default
    private String createBy = "Administrator";

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date modifyDate = new Timestamp(System.currentTimeMillis());

    @Builder.Default
    private String modifyBy = "Administrator";


}
