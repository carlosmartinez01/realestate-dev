package com.maverik.realestate.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.view.bean.PropertyBean;
import com.maverik.realestate.view.bean.UserBean;

@Component
@SonarClassExclusion
public class ProjectSpecialMapper {

    @Autowired
    private UserActiveMapper userActiveMapper;

    public User asUser(UserBean userBean) {
	if (userBean == null) {
	    return null;
	}

	User user = new User();

	user.setUserRoles(userActiveMapper.asSetUserRoles(userBean.getRoles()));
	user.setId(userBean.getId());
	user.setCellPhone(userBean.getCellPhone());
	user.setOfficePhone(userBean.getOfficePhone());
	user.setUsername(userBean.getUsername());
	user.setPassword(userBean.getPassword());
	user.setEmail(userBean.getEmail());
	user.setFirstName(userBean.getFirstName());
	user.setLastName(userBean.getLastName());
	user.setActive(userActiveMapper.asByte(userBean.getActive()));
	user.setCompany(userActiveMapper.asCompany(userBean.getCompany()));

	return user;
    }

    public UserBean asUserBean(User user) {
	if (user == null) {
	    return null;
	}

	UserBean userBean = new UserBean();

	userBean.setRoles(userActiveMapper.asSetString(user.getUserRoles()));
	userBean.setUsername(user.getUsername());
	userBean.setPassword(user.getPassword());
	userBean.setFirstName(user.getFirstName());
	userBean.setLastName(user.getLastName());
	userBean.setEmail(user.getEmail());
	userBean.setActive(userActiveMapper.asBoolean(user.getActive()));
	userBean.setId(user.getId());
	userBean.setCellPhone(user.getCellPhone());
	userBean.setOfficePhone(user.getOfficePhone());
	userBean.setCompany(userActiveMapper.asCompanyBean(user.getCompany()));

	return userBean;
    }

    public Property asProperty(PropertyBean propertyBean) {
	if (propertyBean == null) {
	    return null;
	}

	Property property = new Property();

	property.setName(propertyBean.getName());
	property.setId(propertyBean.getId());
	property.setAddress(propertyBean.getAddress());
	property.setLatitude(propertyBean.getLatitude());
	property.setLongitude(propertyBean.getLongitude());

	return property;
    }

    public PropertyBean asPropertyBean(Property property) {
	if (property == null) {
	    return null;
	}

	PropertyBean propertyBean = new PropertyBean();

	propertyBean.setId(property.getId());
	propertyBean.setName(property.getName());
	propertyBean.setAddress(property.getAddress());
	propertyBean.setLatitude(property.getLatitude());
	propertyBean.setLongitude(property.getLongitude());

	return propertyBean;
    }
}
