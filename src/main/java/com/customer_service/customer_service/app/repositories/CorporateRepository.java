package com.customer_service.customer_service.app.repositories;

import com.customer_service.customer_service.app.model.dbs.CorporateModel;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CorporateRepository extends JpaRepository<CorporateModel, String> {

    boolean existsByCorporateNameEnAndCorporateNameTh(String corporateName, String corporateTh);

    @Query("SELECT c FROM CorporateModel c " +
            "WHERE (:corporateName IS NULL OR :corporateName = '' " +
            "OR c.corporateNameEn LIKE %:corporateName% " +
            "OR c.corporateNameTh LIKE %:corporateName%) "
    )
    Page<CorporateModel> searchCorporate(
            @Param("corporateName") String corporateName,
            Pageable pageable
    );
}
