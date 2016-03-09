package com.maverik.realestate.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.view.bean.UserBean;

@Component
@SonarClassExclusion
public class CompanyUsersMapper {

    @Autowired
    private UserMapper userMapper;

    public Set<User> asSetUser(Set<UserBean> set) {
	if (set == null) {
	    return new HashSet<User>();
	}

	Set<User> userSet = new HashSet<User>();
	for (UserBean userBean : set) {
	    userSet.add(asUser(userBean));
	}

	return userSet;
    }

    public User asUser(UserBean userBean) {
	return userMapper.userBeanToUser(userBean);
    }

    public Set<UserBean> asSetUserBean(Set<User> set) {
	if (set == null) {
	    return new HashSet<UserBean>();
	}

	Set<UserBean> userSet = new HashSet<UserBean>();
	for (User user : set) {
	    userSet.add(asUserBean(user));
	}

	return userSet;
    }

    public UserBean asUserBean(User user) {
	return userMapper.userToUserBean(user);
    }
}
