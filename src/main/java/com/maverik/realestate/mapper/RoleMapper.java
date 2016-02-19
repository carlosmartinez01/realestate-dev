package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.Role;
import com.maverik.realestate.view.bean.RoleBean;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
	RoleBean roleToRoleBean(Role role);
	
	Role roleBeanToRole(RoleBean roleBean);
	
	List<RoleBean> rolesToRoleBeans(List<Role> roles);
	
	List<Role> roleBeansToRoles(List<RoleBean> roleBeans);

}
