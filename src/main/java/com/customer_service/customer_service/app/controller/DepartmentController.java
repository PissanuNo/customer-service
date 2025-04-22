package com.customer_service.customer_service.app.controller;


import com.customer_service.customer_service.app.model.dto.DepartmentRequest;
import com.customer_service.customer_service.app.model.dto.DepartmentResponse;
import com.customer_service.customer_service.app.model.dto.SearchDepartmentRequest;
import com.customer_service.customer_service.app.services.DepartmentService;
import com.customer_service.customer_service.core.model.Permission;
import com.customer_service.customer_service.core.model.RequestBodyModel;
import com.customer_service.customer_service.core.model.ResponseBodyModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.customer_service.customer_service.app.constants.Permissions.menuCode.DEPARTMENT_MANAGEMENT;
import static com.customer_service.customer_service.app.constants.Permissions.permissionFlag.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Permission(menu = DEPARTMENT_MANAGEMENT, permission = CREATE)
    @PostMapping(path = "/s/department",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<String>> createDepartment(@Valid @RequestBody DepartmentRequest request) {
        ResponseBodyModel<String> response = departmentService.createDepartment(request);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = DEPARTMENT_MANAGEMENT, permission = UPDATE)
    @PatchMapping(path = "/s/department",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<String>> updateDepartment(@Valid @RequestBody DepartmentRequest request) {
        ResponseBodyModel<String> response = departmentService.updateDepartment(request);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = DEPARTMENT_MANAGEMENT, permission = READ)
    @GetMapping(path = "/s/department/{departmentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<DepartmentResponse>> getDepartmentById(@PathVariable("departmentId") String departmentId) {
        ResponseBodyModel<DepartmentResponse> response = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = DEPARTMENT_MANAGEMENT, permission = DELETE)
    @DeleteMapping(path = "/s/department/{departmentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<String>> deleteCorporate(@PathVariable("departmentId") String departmentId) {
        ResponseBodyModel<String> response = departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = DEPARTMENT_MANAGEMENT, permission = SEARCH)
    @PostMapping(path = "/s/department/search",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<List<DepartmentResponse>>> searchCorporate(@Valid @RequestBody RequestBodyModel<SearchDepartmentRequest> request) {
        ResponseBodyModel<List<DepartmentResponse>> response = departmentService.searchDepartments(request);
        return ResponseEntity.ok(response);
    }

}
