package com.customer_service.customer_service.app.controller;


import com.customer_service.customer_service.app.model.dto.CorporateRequest;
import com.customer_service.customer_service.app.model.dto.CorporateResponse;
import com.customer_service.customer_service.app.model.dto.SearchCorporateRequest;
import com.customer_service.customer_service.app.services.CorporateService;
import com.customer_service.customer_service.core.model.Permission;
import com.customer_service.customer_service.core.model.RequestBodyModel;
import com.customer_service.customer_service.core.model.ResponseBodyModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.customer_service.customer_service.app.constants.Permissions.menuCode.CORPORATE_MANAGEMENT;
import static com.customer_service.customer_service.app.constants.Permissions.permissionFlag.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class CorporateController {

    private final CorporateService corporateService;

    @Permission(menu = CORPORATE_MANAGEMENT, permission = CREATE)
    @PostMapping(path = "/s/corporate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<String>> createCorporate(@Valid @RequestBody CorporateRequest request) {
        ResponseBodyModel<String> response = corporateService.createCorporate(request);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = CORPORATE_MANAGEMENT, permission = UPDATE)
    @PatchMapping(path = "/s/corporate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<String>> updateCorporate(@Valid @RequestBody CorporateRequest request) {
        ResponseBodyModel<String> response = corporateService.updateCorporate(request);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = CORPORATE_MANAGEMENT, permission = READ)
    @GetMapping(path = "/s/corporate/{corporateId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<CorporateResponse>> getCorporateById(@PathVariable("corporateId") String corporateId) {
        ResponseBodyModel<CorporateResponse> response = corporateService.getCorporateById(corporateId);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = CORPORATE_MANAGEMENT, permission = DELETE)
    @DeleteMapping(path = "/s/corporate/{corporateId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<String>> deleteCorporate(@PathVariable("corporateId") String corporateId) {
        ResponseBodyModel<String> response = corporateService.deleteCorporate(corporateId);
        return ResponseEntity.ok(response);
    }

    @Permission(menu = CORPORATE_MANAGEMENT, permission = SEARCH)
    @PostMapping(path = "/s/corporate/search",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyModel<List<CorporateResponse>>> searchCorporate(@Valid @RequestBody RequestBodyModel<SearchCorporateRequest> request) {
        ResponseBodyModel<List<CorporateResponse>> response = corporateService.searchCorporate(request);
        return ResponseEntity.ok(response);
    }

}
