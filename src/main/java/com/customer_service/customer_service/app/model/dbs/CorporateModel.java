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
@Table(name = "portal_corporate")
public class CorporateModel {
    @Id
    private String corporateId;
    private String corporateNameEn;
    private String corporateNameTh;
    private String coporateParent;
    @Builder.Default
    private Integer hirencyLevel = 0;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "corporateId", insertable = false, updatable = false)
    private CorporateConfigModel corporateConfigModel;

}
