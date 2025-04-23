package com.customer_service.customer_service.app.model.dto.corporate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorporateServiceRequest {

    @NotBlank
    private String corporateId;

    @NotBlank
    private String serviceId;




}
