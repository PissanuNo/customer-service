package com.customer_service.customer_service.app.services;

import com.customer_service.customer_service.app.model.dto.department.DepartmentRequest;
import com.customer_service.customer_service.app.model.dto.department.DepartmentResponse;
import com.customer_service.customer_service.app.model.dto.department.SearchDepartmentRequest;
import com.customer_service.customer_service.core.model.RequestBodyModel;
import com.customer_service.customer_service.core.model.ResponseBodyModel;

import java.util.List;

public interface DepartmentService {

    ResponseBodyModel<String> createDepartment(DepartmentRequest request);

    ResponseBodyModel<String> updateDepartment(DepartmentRequest request);

    ResponseBodyModel<String> deleteDepartment(String  departmentId);

    ResponseBodyModel<DepartmentResponse> getDepartmentById(String departmentId);

    ResponseBodyModel<List<DepartmentResponse>> searchDepartments(RequestBodyModel<SearchDepartmentRequest> request);
}
