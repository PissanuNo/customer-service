package com.customer_service.customer_service.app.repositories;

import com.customer_service.customer_service.app.model.dbs.JobGradeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobGradeRepository extends JpaRepository<JobGradeModel, String> {


}
