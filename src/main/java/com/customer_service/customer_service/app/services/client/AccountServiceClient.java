package com.customer_service.customer_service.app.services.client;

import com.customer_service.customer_service.core.model.ResponseBodyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class AccountServiceClient {
    private final WebClient webClient;

    private static final Logger log = LoggerFactory.getLogger(AccountServiceClient.class);

    @Autowired
    public AccountServiceClient(WebClient.Builder webClientBuilder,
                                @Value("${account.service}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    public ResponseBodyModel<Boolean> hasEmployeeInCorporate(List<String> corporateIdList) {
        return webClient.post()
                .uri("/v3/employee/corporate/exists")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(corporateIdList)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseBodyModel<Boolean>>() {
                })
                .doOnError(WebClientResponseException.class,
                        ex -> log.error("Error has employee in corporate {}", ex.getMessage()))
                .block();
    }

    public ResponseBodyModel<Boolean> hasEmployeeInDepartment(List<String> departmentIdList) {
        return webClient.post()
                .uri("/v3/employee/department/exists")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(departmentIdList)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseBodyModel<Boolean>>() {
                })
                .doOnError(WebClientResponseException.class,
                        ex -> log.error("Error has employee in department {}", ex.getMessage()))
                .block();
    }

}
