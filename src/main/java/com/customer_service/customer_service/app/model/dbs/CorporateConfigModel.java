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
@Table(name = "portal_corporate_config")
public class CorporateConfigModel {
    @Id
    private String corporateId;
    private String logoUri;

    @Builder.Default
    private String primaryColor = "#D1EC51";

    @Builder.Default
    private String fontColorPrimary = "#2B303A";

    @Builder.Default
    private String fontColorSecondary = "#FFFFFF";

    private String fontColorTertiary;

    @Builder.Default
    private String secondaryColor = "#2B303A";

    @Builder.Default
    private String tertiaryColor = "#00BAB3";
    @Builder.Default
    private String quaternaryColor = "#80DCD9";
    @Builder.Default
    private String quinaryColor = "#E0F2EF";

    private String senaryColor;
    private String septenaryColor;
    private String octonaryColor;
    private String nonaryColor;
    private String denaryColor;

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
