package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.view.bean.UserBean;

@Mapper(componentModel = "spring", uses={UserActiveMapper.class})
public interface UserMapper {
	
	@Mapping(source = "userRoles", target = "roles")
	UserBean userToUserBean(User user);
	
	@Mapping(source = "roles", target = "userRoles")
	User userBeanToUser(UserBean userBean);
	
	List<UserBean> listOfUsersToListOfUserBeans(List<User> users);
	
	List<User> listOfUserBeansToListOfUsers(List<UserBean> userBeans);

}