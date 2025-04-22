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
@Table(name = "portal_service_module")
public class ServiceModuleModel {
    @Id
    private String serviceId;
    private String serviceNameEn;
    private String serviceNameTh;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createDate = new Timestamp(System.currentTimeMillis());

    @Builder.Default
    private String createBy = "Administrator";

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date modifyDate = new Timestamp(System.currentTimeMillis());

    @Builder.Default
    private String modifyBy = "Administrator";

}
