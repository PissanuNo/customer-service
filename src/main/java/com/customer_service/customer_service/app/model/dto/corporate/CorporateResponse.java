package com.customer_service.customer_service.app.model.dto.corporate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorporateResponse {
    private String corporateId;
    private String corporateNameEn;
    private String corporateNameTh;
    private String coporateParent;
    private Integer hirencyLevel;
    private CorporateConfig corporateConfig;
}
