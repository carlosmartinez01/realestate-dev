package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.Company;
import com.maverik.realestate.view.bean.CompanyBean;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	@Mapping(source = "users", target = "users", ignore=true)
	Company companyBeanToCompany(CompanyBean companyBean);
	
	@Mapping(source = "users", target = "users", ignore=true)
	CompanyBean companyToCompanyBean(Company company);
	
	List<Company> listOfCompanyBeansToListOfCompany(List<CompanyBean> companyBeans);
	
	List<CompanyBean> listOfCompanyToListOfCompanyBeans(List<Company> companies);

}
