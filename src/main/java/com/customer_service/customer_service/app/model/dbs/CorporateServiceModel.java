package com.customer_service.customer_service.app.model.dbs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portal_corporate_service")
public class CorporateServiceModel {
    @Id
    private String tranId;
    private String corporateId;
    private String serviceId;

}
