package com.customer_service.customer_service.app.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorporateRequest {
    private String corporateId;

    @NotBlank
    private String corporateNameEn;

    @NotBlank
    private String corporateNameTh;

    private String corporateParent;

    private Integer hirencyLevel;

    private CorporateConfig corporateConfig;


}
