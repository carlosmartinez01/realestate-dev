/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.view.bean.UserBean;

/**
 * @author jorge
 *
 */
@Component
@SonarClassExclusion
public class ProjectUserMapper {

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

}
