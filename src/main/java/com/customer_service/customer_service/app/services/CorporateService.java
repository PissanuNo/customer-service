package com.customer_service.customer_service.app.services;

import com.customer_service.customer_service.app.model.dto.corporate.CorporateRequest;
import com.customer_service.customer_service.app.model.dto.corporate.CorporateResponse;
import com.customer_service.customer_service.app.model.dto.corporate.CorporateServiceRequest;
import com.customer_service.customer_service.app.model.dto.corporate.SearchCorporateRequest;
import com.customer_service.customer_service.core.model.RequestBodyModel;
import com.customer_service.customer_service.core.model.ResponseBodyModel;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CorporateService {

    @Transactional
    ResponseBodyModel<String> createCorporate(CorporateRequest request);

    @Transactional
    ResponseBodyModel<String> updateCorporate(CorporateRequest request);

    ResponseBodyModel<CorporateResponse> getCorporateById(String corporateId);

    ResponseBodyModel<String> deleteCorporate(String corporateId);

    ResponseBodyModel<List<CorporateResponse>> searchCorporate(RequestBodyModel<SearchCorporateRequest> request);

    @Transactional
    ResponseBodyModel<String> addCorporateService(CorporateServiceRequest request);

    @Transactional
    ResponseBodyModel<String> removeCorporateService(String corporateServiceId);
}
