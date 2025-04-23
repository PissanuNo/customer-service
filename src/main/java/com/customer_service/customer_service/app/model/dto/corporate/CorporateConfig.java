package com.customer_service.customer_service.app.model.dto.corporate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorporateConfig {
    private String corporateId;
    private String logoUri;
    private String primaryColor;
    private String fontColorPrimary;
    private String fontColorSecondary;
    private String fontColorTertiary;
    private String secondaryColor;
    private String tertiaryColor;
    private String quaternaryColor;
    private String quinaryColor;
    private String senaryColor;
    private String septenaryColor;
    private String octonaryColor;
    private String nonaryColor;
    private String denaryColor;
}
