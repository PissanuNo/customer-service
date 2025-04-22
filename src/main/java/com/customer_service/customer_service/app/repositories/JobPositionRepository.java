package com.customer_service.customer_service.app.repositories;

import com.customer_service.customer_service.app.model.dbs.JobPositionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionRepository extends JpaRepository<JobPositionModel, String> {


}
