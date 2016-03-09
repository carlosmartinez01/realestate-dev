package com.maverik.realestate.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.Company;
import com.maverik.realestate.domain.entity.Role;
import com.maverik.realestate.domain.entity.UserRoles;
import com.maverik.realestate.view.bean.CompanyBean;

@Component
@SonarClassExclusion
public class UserActiveMapper {

    public Boolean asBoolean(Byte active) {
	if (active != null) {
	    if (active == 0) {
		return Boolean.TRUE;
	    } else if (active == 1) {
		return Boolean.FALSE;
	    }
	}
	return Boolean.FALSE;
    }

    public Byte asByte(Boolean active) {
	if (active != null && active) {
	    return 0;
	}
	return 1;
    }

    public Company asCompany(CompanyBean companyBean) {
	if (companyBean == null) {
	    return null;
	}

	Company company = new Company();

	company.setId(companyBean.getId());
	company.setCompanyName(companyBean.getCompanyName());
	company.setAddress(companyBean.getAddress());
	company.setSuite(companyBean.getSuite());
	company.setCity(companyBean.getCity());
	company.setState(companyBean.getState());
	company.setZipCode(companyBean.getZipCode());
	company.setActive(companyBean.getActive());

	return company;
    }

    public CompanyBean asCompanyBean(Company company) {
	if (company == null) {
	    return null;
	}

	CompanyBean companyBean = new CompanyBean();

	companyBean.setId(company.getId());
	companyBean.setCompanyName(company.getCompanyName());
	companyBean.setAddress(company.getAddress());
	companyBean.setSuite(company.getSuite());
	companyBean.setCity(company.getCity());
	companyBean.setState(company.getState());
	companyBean.setZipCode(company.getZipCode());
	companyBean.setActive(company.getActive());

	return companyBean;
    }

    public Set<String> asSetString(Set<UserRoles> userRoles) {
	if (userRoles == null) {
	    return new HashSet<String>();
	}

	Set<String> s = new HashSet<String>();
	for (UserRoles ur : userRoles) {
	    s.add(userRoleToString(ur));
	}

	return s;
    }

    public Set<UserRoles> asSetUserRoles(Set<String> roles) {
	if (roles == null) {
	    return new HashSet<UserRoles>();
	}

	Set<UserRoles> ur = new HashSet<UserRoles>();
	for (String s : roles) {
	    ur.add(stringToUserRole(s));
	}

	return ur;
    }

    private String userRoleToString(UserRoles userRoles) {
	if (userRoles == null) {
	    return null;
	}

	return userRoles.getRoleId().getRoleName();
    }

    private UserRoles stringToUserRole(String role) {
	if (role == null) {
	    return null;
	}

	UserRoles ur = new UserRoles();
	Role r = new Role();
	r.setRoleName(role);
	ur.setRoleId(r);

	return ur;
    }
}
