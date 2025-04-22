package com.customer_service.customer_service.app.repositories;

import com.customer_service.customer_service.app.model.dbs.CorporateServiceModel;
import com.customer_service.customer_service.app.model.dbs.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateServiceRepository extends JpaRepository<CorporateServiceModel, String> {


}

