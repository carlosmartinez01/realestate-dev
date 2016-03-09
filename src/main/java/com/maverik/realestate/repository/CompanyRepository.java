package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Company;

@Repository(value="companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query("SELECT c FROM Company c where c.active = true")
	List<Company> findAllCompanies();

	@Modifying
	@Query("UPDATE Company set active = false where id = :companyId")
	void logicalDeleteCompany(@Param("companyId") Long companyId);
	
	@Query("SELECT c FROM Company c where c.companyName = :name")
	Company findByCompanyName(@Param("name") String name);
}
