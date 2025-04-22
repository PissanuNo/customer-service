package com.customer_service.customer_service.app.services.impl;

import com.customer_service.customer_service.app.model.dbs.CorporateConfigModel;
import com.customer_service.customer_service.app.model.dbs.CorporateModel;
import com.customer_service.customer_service.app.model.dto.CorporateConfig;
import com.customer_service.customer_service.app.model.dto.CorporateRequest;
import com.customer_service.customer_service.app.model.dto.CorporateResponse;
import com.customer_service.customer_service.app.model.dto.SearchCorporateRequest;
import com.customer_service.customer_service.app.repositories.CorporateConfigRepository;
import com.customer_service.customer_service.app.repositories.CorporateRepository;
import com.customer_service.customer_service.app.services.CorporateService;
import com.customer_service.customer_service.app.services.UtilService;
import com.customer_service.customer_service.app.services.client.AccountServiceClient;
import com.customer_service.customer_service.core.model.RequestBodyModel;
import com.customer_service.customer_service.core.model.ResponseBodyModel;
import com.customer_service.customer_service.core.service.PrincipalService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.customer_service.customer_service.app.constants.Constant.ResponseCode.*;
import static com.customer_service.customer_service.app.constants.Constant.ResponseMessage.*;


@Service
@RequiredArgsConstructor
public class CorporateServiceImpl implements CorporateService {

    private static final Logger log = LoggerFactory.getLogger(CorporateServiceImpl.class);
    private final CorporateRepository corporateRepository;
    private final PrincipalService principalService;
    private final UtilService utilService;
    private final AccountServiceClient accountServiceClient;
    private final CorporateConfigRepository corporateConfigRepository;

    @Transactional
    @Override
    public ResponseBodyModel<String> createCorporate(CorporateRequest request) {
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try {
            //check duplicate corporate name en/th
            if (checkDuplicateCorporateName(request.getCorporateNameEn(), request.getCorporateNameTh())) {
                response.setOperationError(ERROR_CODE_BUSINESS, DATA_DUPLICATE, null);
                return response;
            }
            String userId = principalService.getUserId();
            String corporateId = UUID.randomUUID().toString();
            Date now = new Timestamp(System.currentTimeMillis());

            corporateRepository.saveAndFlush(CorporateModel.builder()
                    .corporateId(corporateId)
                    .corporateNameTh(request.getCorporateNameEn())
                    .corporateNameEn(request.getCorporateNameTh())
                    .coporateParent(request.getCorporateParent())
                    .hirencyLevel(request.getHirencyLevel())
                    .createBy(userId)
                    .createDate(now)
                    .modifyBy(userId)
                    .modifyDate(now)
                    .build());

            request.getCorporateConfig().setCorporateId(corporateId);

            saveCorporateConfig("CREATE",
                    userId,
                    new CorporateConfigModel(),
                    request.getCorporateConfig());

            response.setOperationSuccess(SUCCESS_CODE, SUCCESS, null);
        } catch (Exception ex) {
            log.error("Error creating corporate", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    private void saveCorporateConfig(String action, String userId, CorporateConfigModel configModel, CorporateConfig request) {
        Date now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("CREATE")) {
            configModel.setCreateBy(userId);
            configModel.setCreateDate(now);
        }
        configModel.setCorporateId(request.getCorporateId());
        configModel.setLogoUri(request.getLogoUri());
        configModel.setFontColorPrimary(request.getFontColorPrimary());
        configModel.setFontColorSecondary(request.getFontColorSecondary());
        configModel.setPrimaryColor(request.getPrimaryColor());
        configModel.setSecondaryColor(request.getSecondaryColor());
        configModel.setTertiaryColor(request.getTertiaryColor());
        configModel.setQuaternaryColor(request.getQuaternaryColor());
        configModel.setQuinaryColor(request.getQuinaryColor());
        configModel.setSenaryColor(request.getSenaryColor());
        configModel.setSeptenaryColor(request.getSeptenaryColor());
        configModel.setOctonaryColor(request.getOctonaryColor());
        configModel.setNonaryColor(request.getNonaryColor());
        configModel.setDenaryColor(request.getDenaryColor());
        configModel.setModifyBy(userId);
        configModel.setModifyDate(now);
        corporateConfigRepository.saveAndFlush(configModel);
    }

    @Transactional
    @Override
    public ResponseBodyModel<String> updateCorporate(CorporateRequest request) {
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try {
            Optional<CorporateModel> corporateModel = corporateRepository.findById(request.getCorporateId());
            if (corporateModel.isPresent()) {
                String userId = principalService.getUserId();

                if (!corporateModel.get().getCorporateNameEn().equals(request.getCorporateNameEn()) &&
                        !corporateModel.get().getCorporateNameTh().equals(request.getCorporateNameTh()) &&
                        checkDuplicateCorporateName(request.getCorporateNameEn(), request.getCorporateNameTh())) {
                    response.setOperationError(ERROR_CODE_BUSINESS, DATA_DUPLICATE, null);
                    return response;
                }
                corporateModel.get().setCorporateNameEn(request.getCorporateNameEn());
                corporateModel.get().setCorporateNameTh(request.getCorporateNameTh());
                corporateModel.get().setCoporateParent(request.getCorporateParent());
                corporateModel.get().setHirencyLevel(request.getHirencyLevel());
                corporateModel.get().setModifyBy(userId);
                corporateModel.get().setModifyDate(new Timestamp(System.currentTimeMillis()));
                corporateRepository.saveAndFlush(corporateModel.get());

                saveCorporateConfig("UPDATE",
                        userId,
                        corporateModel.get().getCorporateConfigModel(),
                        request.getCorporateConfig());
                response.setOperationSuccess(SUCCESS_CODE, SUCCESS, null);
            } else {
                response.setOperationError(ERROR_CODE_DATA_NOT_FOUND, DATA_NOT_FOUND, null);
            }
        } catch (Exception ex) {
            log.error("Error updating corporate", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    @Override
    public ResponseBodyModel<CorporateResponse> getCorporateById(String corporateId) {
        ResponseBodyModel<CorporateResponse> response = new ResponseBodyModel<>();
        try {
            Optional<CorporateModel> corporateOpt = corporateRepository.findById(corporateId);
            if (corporateOpt.isPresent()) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                CorporateResponse result = mapper.convertValue(corporateOpt.get(), CorporateResponse.class);
                result.setCorporateConfig(mapper.convertValue(corporateOpt.get().getCorporateConfigModel(), CorporateConfig.class));
                response.setOperationSuccess(SUCCESS_CODE, SUCCESS, result);
            } else {
                response.setOperationError(ERROR_CODE_DATA_NOT_FOUND, DATA_NOT_FOUND, null);
            }
        } catch (Exception ex) {
            log.error("Error getting corporate", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    private boolean checkDuplicateCorporateName(String corporateNameEn, String corporateNameTh) {
        return corporateRepository.existsByCorporateNameEnAndCorporateNameTh(corporateNameEn, corporateNameTh);
    }

    @Override
    public ResponseBodyModel<String> deleteCorporate(String corporateId) {
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try {
            //check have user use corporate
            ResponseBodyModel<Boolean> result = accountServiceClient
                    .hasEmployeeInCorporate(List.of(corporateId));
            if (result.getObjectValue().equals(Boolean.TRUE)) {
//                corporateRepository.deleteById(corporateId);
                response.setOperationSuccess(SUCCESS_CODE, SUCCESS, null);
            } else {
                response.setOperationError(ERROR_CODE_BUSINESS, ERROR, null);
            }
        } catch (Exception ex) {
            log.error("Error deleting corporate", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    @Override
    public ResponseBodyModel<List<CorporateResponse>> searchCorporate(RequestBodyModel<SearchCorporateRequest> request) {
        ResponseBodyModel<List<CorporateResponse>> response = new ResponseBodyModel<>();
        try {
            Pageable pageable = utilService.pageBodyconvertToPageable(request.getPageValue());

            Page<CorporateModel> searched = corporateRepository.searchCorporate(request.getRequestObject().getCorporateName(),
                    pageable);

            List<CorporateResponse> result = searched.stream()
                    .map(CorporateResponse.class::cast)
                    .toList();

            response.setPageValue(utilService.pageableConvertToPageBodyModel(
                    searched.getPageable(),
                    searched.getTotalElements(),
                    searched.getTotalPages()));
            response.setOperationSuccess(SUCCESS_CODE, SUCCESS, result);
        } catch (Exception ex) {
            log.error("Error searching corporate", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }

    public ResponseBodyModel<String> addCorporateService(String corporateId, String serviceId){
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try{
            //check corporate
            //check service
            //ass corporate service

        }catch (Exception ex){
            log.error("Error adding corporate service", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }
}
