package com.maverik.realestate.service;

import java.util.List;
import java.util.Set;

import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.RoleBean;

public interface RoleManagementService {

    public RoleBean insertRole(RoleBean role) throws DBException;

    public RoleBean updateRole(RoleBean role) throws DBException;

    public void deleteRole(RoleBean role) throws DBException;

    public RoleBean findByRole(Long id) throws DBException;

    public List<RoleBean> findAllRoles() throws DBException;

    public List<RoleBean> findAllRolesNotDefault(String roleName)
	    throws DBException;

    public String getUserRoleSelected(Set<String> roles);

    public RoleBean findByRoleName(String name) throws GenericException;
}
