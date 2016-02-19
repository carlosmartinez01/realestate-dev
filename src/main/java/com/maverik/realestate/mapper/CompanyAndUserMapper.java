package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.Company;
import com.maverik.realestate.view.bean.CompanyBean;

@Mapper(componentModel = "spring", uses=CompanyUsersMapper.class)
public interface CompanyAndUserMapper {
	
	Company companyBeanToCompany(CompanyBean companyBean);
	
	CompanyBean companyToCompanyBean(Company company);
	
	List<Company> listOfCompanyBeansToListOfCompany(List<CompanyBean> companyBeans);
	
	List<CompanyBean> listOfCompanyToListOfCompanyBeans(List<Company> companies);

}
