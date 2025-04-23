package com.customer_service.customer_service.app.model.dto.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponse {

    private String departmentId;

    private String departmentNameEn;

    private String departmentNameTh;

    private String corporateId;

    private Integer hierarchyLevel;

    private String parentDepartmentId;

}
