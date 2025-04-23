package com.customer_service.customer_service.app.services.impl;

import com.customer_service.customer_service.app.repositories.JobGradeRepository;
import com.customer_service.customer_service.app.repositories.JobPositionRepository;
import com.customer_service.customer_service.app.services.PositionService;
import com.customer_service.customer_service.core.model.ResponseBodyModel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.customer_service.customer_service.app.constants.Constant.ResponseCode.INTERNAL_SERVER_ERROR;
import static com.customer_service.customer_service.app.constants.Constant.ResponseMessage.INTERNAL_SERVER_ERROR_MSG;


@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private static final Logger log = LoggerFactory.getLogger(PositionServiceImpl.class);
    private final JobPositionRepository positionRepository;
    private final JobGradeRepository jobGradeRepository;


    public ResponseBodyModel<String> createPosition() {
        ResponseBodyModel<String> response = new ResponseBodyModel<>();
        try {



        } catch (Exception ex) {
            log.error("Error creating position", ex);
            response.setOperationError(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, null);
        }
        return response;
    }


}
