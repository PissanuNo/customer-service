package com.customer_service.customer_service.app.repositories;

import com.customer_service.customer_service.app.model.dbs.ServiceModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateModuleRepository extends JpaRepository<ServiceModuleModel, String> {


}

