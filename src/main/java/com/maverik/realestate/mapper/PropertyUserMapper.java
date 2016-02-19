/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PropertyContract;
import com.maverik.realestate.domain.entity.PropertyPermitting;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.view.bean.PropertyContractBean;
import com.maverik.realestate.view.bean.PropertyPermittingBean;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@Component
@SonarClassExclusion
public class PropertyUserMapper {

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

    public PropertyContract asPropertyContract(PropertyContractBean contract) {

	if (contract == null) {
	    return null;
	}

	PropertyContract c = new PropertyContract();
	c.setId(contract.getId());

	return c;
    }

    public PropertyContractBean asPropertyContractBean(PropertyContract contract) {

	if (contract == null) {
	    return null;
	}

	PropertyContractBean c = new PropertyContractBean();
	c.setId(contract.getId());
	c.setContractType(contract.getContractType());
	c.setPropertyId(contract.getPropertyId().getId());

	return c;
    }

    public PropertyPermitting asPropertyPermitting(
	    PropertyPermittingBean permitting) {

	if (permitting == null) {
	    return null;
	}

	PropertyPermitting p = new PropertyPermitting();
	p.setId(permitting.getId());

	return p;
    }

    public PropertyPermittingBean asPropertyPermittingBean(
	    PropertyPermitting permitting) {

	if (permitting == null) {
	    return null;
	}

	PropertyPermittingBean p = new PropertyPermittingBean();
	p.setId(permitting.getId());
	p.setPropertyId(permitting.getPropertyId().getId());

	return p;
    }
}
