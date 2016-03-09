package com.maverik.realestate.service;

import java.util.List;

import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.UserBean;

public interface UserManagementService {

    public void resetPasswordForUser(String username) throws DBException;

    public UserBean getUserProfileActive(String username, byte flag)
	    throws DBException;

    public UserBean updateUserProfile(UserBean user, String previousPassword)
	    throws DBException;

    public UserBean addUserToCompany(UserBean user, String roleName)
	    throws DBException;

    public List<UserBean> getAllUsers() throws DBException;

    public UserBean getUserProfile(Long id) throws DBException;

    public void deleteUser(UserBean user) throws DBException;

    public void deleteUserWithCompany(Long userId) throws DBException;

    public UserBean getActiveUserAndRoles(String username) throws DBException;

    public void deleteUserRolesWhenUpdateProfile(UserBean user)
	    throws DBException;

    public Integer changeUserPassword(Long userId, String password)
	    throws GenericException;
}
