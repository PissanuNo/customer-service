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
public class DepartmentRequest {

    private String departmentId;

    @NotBlank
    private String departmentNameEn;

    @NotBlank
    private String departmentNameTh;

    @NotBlank
    private String corporateId;

    private Integer hierarchyLevel;

    private String parentDepartmentId;

}
