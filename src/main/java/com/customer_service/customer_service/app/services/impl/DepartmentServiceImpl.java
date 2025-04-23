package com.customer_service.customer_service.app.services.impl;

import com.customer_service.customer_service.app.model.dbs.DepartmentModel;
import com.customer_service.customer_service.app.model.dto.department.DepartmentRequest;
import com.customer_service.customer_service.app.model.dto.department.DepartmentResponse;
import com.customer_service.customer_service.app.model.dto.department.SearchDepartmentRequest;
import com.customer_service.customer_service.app.repositories.DepartmentRepository;
import com.customer_service.customer_service.app.services.DepartmentService;
import com.customer_service.customer_service.app.services.UtilService;
import com.customer_service.customer_service.app.services.client.AccountServiceClient;
import com.customer_service.customer_service.core.model.RequestBodyModel;
import com.customer_service.customer_service.core.model.ResponseBodyModel;
import com.customer_service.customer_service.core.service.PrincipalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.customer_service.customer_service.app.constants.Constant.ResponseCode.*;
import static com.customer_service.customer_service.app.constants.Constant.ResponseMessage.*;


@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository departmentRepository;
    private final PrincipalService principalService;
    private final AccountServiceClient accountServiceClient;
    private final UtilService utilService;

    @Transactional
    @Override
    public ResponseBodyModel<String> createDepartment(DepartmentRequest request) {
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try {
            String userId = principalService.getUserId();
            String departmentId = UUID.randomUUID().toString();
            departmentRepository.saveAndFlush(DepartmentModel.builder()
                    .departmentId(departmentId)
                    .departmentNameEn(request.getDepartmentNameEn())
                    .departmentNameTh(request.getDepartmentNameTh())
                    .corporateId(request.getCorporateId())
                    .hierarchyLevel(request.getHierarchyLevel())
                    .createDate(new Timestamp(System.currentTimeMillis()))
                    .createBy(userId)
                    .modifyDate(new Timestamp(System.currentTimeMillis()))
                    .modifyBy(userId)
                    .build());
            response.setOperationSuccess(SUCCESS_CODE, SUCCESS, null);
        } catch (Exception ex) {
            log.error("Error while creating department", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    @Transactional
    @Override
    public ResponseBodyModel<String> updateDepartment(DepartmentRequest request) {
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try {
            String userId = principalService.getUserId();
            Optional<DepartmentModel> departmentOpt = departmentRepository.findById(request.getDepartmentId());
            if (departmentOpt.isPresent()) {
                departmentOpt.get().setDepartmentNameEn(request.getDepartmentNameEn());
                departmentOpt.get().setDepartmentNameTh(request.getDepartmentNameTh());
                departmentOpt.get().setCorporateId(request.getCorporateId());
                departmentOpt.get().setHierarchyLevel(request.getHierarchyLevel());
                departmentOpt.get().setModifyDate(new Timestamp(System.currentTimeMillis()));
                departmentOpt.get().setModifyBy(userId);
                departmentRepository.saveAndFlush(departmentOpt.get());
                response.setOperationSuccess(SUCCESS_CODE, SUCCESS, null);
            } else {
                response.setOperationError(ERROR_CODE_DATA_NOT_FOUND, DATA_NOT_FOUND, null);
            }

        } catch (Exception ex) {
            log.error("Error while updating department", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    @Transactional
    @Override
    public ResponseBodyModel<String> deleteDepartment(String departmentId) {
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try {

            Optional<DepartmentModel> departmentModel = departmentRepository.findById(departmentId);
            if (departmentModel.isPresent()) {
                //check have user use
                ResponseBodyModel<Boolean> result = accountServiceClient.hasEmployeeInDepartment(List.of(departmentModel.get().getDepartmentId()));
                if (result.getObjectValue().equals(Boolean.TRUE)) {
//                    departmentRepository.deleteById(request.getDepartmentId());
                    response.setOperationSuccess(SUCCESS_CODE, SUCCESS, null);
                } else {
                    response.setOperationError(ERROR_CODE_BUSINESS, ERROR, null);
                }

            } else {
                response.setOperationError(ERROR_CODE_DATA_NOT_FOUND, DATA_NOT_FOUND, null);
            }

        } catch (Exception ex) {
            log.error("Error while deleting department", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    @Override
    public ResponseBodyModel<DepartmentResponse> getDepartmentById(String departmentId) {
        ResponseBodyModel<DepartmentResponse> response = new ResponseBodyModel<>();
        try {
            Optional<DepartmentModel> departmentOpt = departmentRepository.findById(departmentId);
            if (departmentOpt.isPresent()) {
                response.setOperationSuccess(SUCCESS_CODE, SUCCESS,
                        DepartmentResponse.builder()
                                .departmentId(departmentOpt.get().getDepartmentId())
                                .departmentNameEn(departmentOpt.get().getDepartmentNameEn())
                                .departmentNameTh(departmentOpt.get().getDepartmentNameTh())
                                .corporateId(departmentOpt.get().getCorporateId())
                                .hierarchyLevel(departmentOpt.get().getHierarchyLevel())
                                .build());
            } else {
                response.setOperationError(ERROR_CODE_DATA_NOT_FOUND, DATA_NOT_FOUND, null);
            }

        } catch (Exception ex) {
            log.error("Error while getting department", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    @Override
    public ResponseBodyModel<List<DepartmentResponse>> searchDepartments(RequestBodyModel<SearchDepartmentRequest> request) {
        ResponseBodyModel<List<DepartmentResponse>> response = new ResponseBodyModel<>();
        try{
            Pageable pageable = utilService.pageBodyconvertToPageable(request.getPageValue());

            Page<DepartmentModel> searched = departmentRepository.searchDepartment(request.getRequestObject().getDepartmentName(),
                    request.getRequestObject().getCorporateId(),
                    pageable);

            List<DepartmentResponse> result = searched.stream()
                    .map(DepartmentResponse.class::cast)
                    .toList();

            response.setPageValue(utilService.pageableConvertToPageBodyModel(
                    searched.getPageable(),
                    searched.getTotalElements(),
                    searched.getTotalPages()));

            response.setOperationSuccess(SUCCESS_CODE, SUCCESS, result);
        }catch (Exception ex){
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

}
