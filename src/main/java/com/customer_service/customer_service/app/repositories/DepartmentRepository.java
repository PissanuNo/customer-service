package com.customer_service.customer_service.app.repositories;

import com.customer_service.customer_service.app.model.dbs.DepartmentModel;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, String> {

    @Query("SELECT d FROM DepartmentModel d " +
            "WHERE (:departmentName IS NULL OR :departmentName = '' " +
            "OR d.departmentNameEn LIKE %:departmentName% " +
            "OR d.departmentNameTh LIKE %:departmentName%) " +
            "AND (:corporateId IS NULL OR :corporateId = '' OR d.corporateId = :corporateId) "
    )
    Page<DepartmentModel> searchDepartment(@Param("departmentName") String departmentName,
                                           @Param("corporateId") String corporateId,
                                           Pageable pageable);

}

