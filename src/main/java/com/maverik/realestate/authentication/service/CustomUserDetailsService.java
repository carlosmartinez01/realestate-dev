package com.maverik.realestate.authentication.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.exception.NoUserFoundException;
import com.maverik.realestate.service.UserManagementService;
import com.maverik.realestate.view.bean.ActiveUser;
import com.maverik.realestate.view.bean.UserBean;

@SonarClassExclusion
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserManagementService userManagementService;

    @Override
    public UserDetails loadUserByUsername(String username) {

	LOGGER.info("Retrieve user and roles from DB by username");

	UserBean user;
	try {
	    user = userManagementService.getActiveUserAndRoles(username);
	} catch (GenericException e) {
	    LOGGER.debug("loadUserByUsername()" + e);
	    throw new UsernameNotFoundException("No User found",
		    new NoUserFoundException("", "User does not exist",
			    e.getFullStackMsg()));
	}

	if (user == null) {
	    throw new UsernameNotFoundException("No User found",
		    new NoUserFoundException("", "User does not exist", null));
	}

	List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

	return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<String> userRoles) {
	LOGGER.info("Build authorities");

	Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

	// Build user's authorities
	for (String role : userRoles) {
	    setAuths.add(new SimpleGrantedAuthority(role));
	}

	return new ArrayList<GrantedAuthority>(setAuths);
    }

    private ActiveUser buildUserForAuthentication(UserBean user,
	    List<GrantedAuthority> authorities) {

	ActiveUser activeUser = new ActiveUser(user.getUsername(),
		user.getPassword(), user.getActive(), true, true, true,
		authorities);
	activeUser.setUserFullName(user.getFirstName() + " "
		+ user.getLastName());

	return activeUser;
    }
}
