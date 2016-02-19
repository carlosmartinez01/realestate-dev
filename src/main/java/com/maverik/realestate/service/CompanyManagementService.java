package com.maverik.realestate.service;

import java.util.List;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.CompanyBean;

public interface CompanyManagementService {
	
	public CompanyBean insertCompany(CompanyBean company) throws GenericException;
	
	public CompanyBean updateCompany(CompanyBean company) throws GenericException;
	
	public void deleteCompany(CompanyBean company) throws GenericException;
	
	public CompanyBean findByCompany(Long id) throws GenericException;
	
	public List<CompanyBean> findAllCompanies() throws GenericException;
	
	public void softDeleteCompany(Long id) throws GenericException;
	
	public CompanyBean findByCompanyName(String name) throws GenericException;
}
