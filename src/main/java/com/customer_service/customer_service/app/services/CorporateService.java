package com.customer_service.customer_service.app.services;

import com.customer_service.customer_service.app.model.dto.CorporateRequest;
import com.customer_service.customer_service.app.model.dto.CorporateResponse;
import com.customer_service.customer_service.app.model.dto.SearchCorporateRequest;
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
}
