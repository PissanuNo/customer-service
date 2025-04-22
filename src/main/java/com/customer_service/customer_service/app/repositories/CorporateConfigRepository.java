package com.customer_service.customer_service.app.repositories;

import com.customer_service.customer_service.app.model.dbs.CorporateConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateConfigRepository extends JpaRepository<CorporateConfigModel, String> {

}
