package com.maverik.realestate.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.constants.RealEstateConstants.Roles;
import com.maverik.realestate.constants.RealEstateConstants.UserInfo;
import com.maverik.realestate.domain.entity.Role;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.domain.entity.UserRoles;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.exception.NoUserFoundException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.UserMapper;
import com.maverik.realestate.repository.RoleRepository;
import com.maverik.realestate.repository.UserRepository;
import com.maverik.realestate.repository.UserRoleRepository;
import com.maverik.realestate.utils.PasswordEncryption;
import com.maverik.realestate.utils.RandomPasswordGenerator;
import com.maverik.realestate.utils.SendMailUtil;
import com.maverik.realestate.view.bean.UserBean;

@SonarClassExclusion
@Service
public class UserManagementServiceImpl implements UserManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(UserManagementServiceImpl.class);
    private String unmaskPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Autowired
    private Environment env;

    @Autowired
    private SendMailUtil client;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void resetPasswordForUser(String username) throws DBException {
	LOGGER.info("Reset Password");
	User user = null;
	try {
	    user = userRepository.findByUsername(username);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	if (user == null) {
	    throw new DBException("", env.getProperty("exception.no.user"), "");
	}
	user.setPassword(encrypt(generatePassword()));
	try {
	    userRepository.save(user);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	client.sendMail(user.getEmail(), user.getUsername(), unmaskPassword);
    }

    private String generatePassword() {
	LOGGER.info("Generate random Password");
	unmaskPassword = RandomPasswordGenerator.generateKey();
	return unmaskPassword;
    }

    private String encrypt(String text) {
	LOGGER.info("Encrypt Password");
	return PasswordEncryption.md5Encoder(text);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserBean getActiveUserProfile(String username)
	    throws DBException {
	LOGGER.info("Get User profile for Active one");
	User user = null;
	try {
	    user = userRepository.findByUsernameAndActive(username, Byte.valueOf(UserInfo.ACTIVE.toString()));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	if (user == null) {
	    throw new DBException("", env.getProperty("exception.no.user"),
		    "-1");
	}

	return mapper.userToUserBean(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserBean updateUserProfile(UserBean userProfile,
	    String previousPassword) throws DBException {
	LOGGER.info("Update {} user profile", userProfile.getUsername());
	String password = null;
	if (previousPassword != null
		&& !previousPassword.equals(userProfile.getPassword())) {
	    password = PasswordEncryption.md5Encoder(userProfile.getPassword());
	    userProfile.setPassword(password);
	}
	User user = mapper.userBeanToUser(userProfile);
	Set<String> rolesSelected = userProfile.getRoles();
	if (rolesSelected != null && !rolesSelected.isEmpty()) {
	    boolean isDefaultRole = false;
	    Set<UserRoles> userRoles = new HashSet<UserRoles>();
	    for (String r : rolesSelected) {
		if (r.equalsIgnoreCase(Roles.USER.toString())) {
		    isDefaultRole = true;
		}
		Role role = null;
		try {
		    role = roleRepository.findByRoleName(r);
		} catch (DataAccessException ex) {
		    LOGGER.debug(ex.getMessage());
		    LOGGER.info(ex.getMostSpecificCause().toString());
		    throw exceptionHandler.getException(ex);
		}
		UserRoles ur = new UserRoles();
		ur.setRoleId(role);
		ur.setUsernameId(user);
		userRoles.add(ur);
		user.setUserRoles(userRoles);
	    }

	    if (!isDefaultRole) {
		Role role = null;
		try {
		    role = roleRepository.findByRoleName(Roles.USER.toString());
		} catch (DataAccessException ex) {
		    LOGGER.debug(ex.getMessage());
		    LOGGER.info(ex.getMostSpecificCause().toString());
		    throw exceptionHandler.getException(ex);
		}
		UserRoles ur = new UserRoles();
		ur.setRoleId(role);
		ur.setUsernameId(user);
		userRoles.add(ur);
		user.setUserRoles(userRoles);
	    }

	}
	try {
	    user = userRepository.save(user);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return mapper.userToUserBean(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserRolesWhenUpdateProfile(UserBean userProfile) {
	LOGGER.info("Delete user roles for {} user" + userProfile.getUsername());
	User user = userRepository.findOne(userProfile.getId());
	if (user != null) {
	    Set<Long> userRoles = new HashSet<Long>();
	    for (UserRoles ur : user.getUserRoles()) {
		userRoles.add(ur.getId());
	    }
	    userRoleRepository.deleteUserRolesWithAssociation(userRoles);
	}
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public UserBean addUserToCompany(UserBean user, String roleName)
	    throws DBException {
	LOGGER.info("Add new user {} to Company", user);
	user.setPassword(encrypt(user.getPassword()));
	User entity = mapper.userBeanToUser(user);
	try {
	    entity = userRepository.save(entity);
	    List<Role> roles = new ArrayList<Role>();
	    Role defaultRole = roleRepository.findByRoleName(Roles.USER
		    .toString());
	    Role role = roleRepository.findByRoleName(roleName);
	    if (defaultRole == null) {
		throw new DBException("No such Role - Please validate it",
			"Unable to process the request - "
				+ "Please contact support team",
			"No error code");
	    } else {
		roles.add(defaultRole);
	    }

	    if (role != null && role != defaultRole) {
		roles.add(role);
	    }

	    List<UserRoles> userRoles = new ArrayList<UserRoles>();
	    for (Role r : roles) {
		UserRoles userRole = new UserRoles();
		userRole.setRoleId(r);
		userRole.setUsernameId(entity);
		userRoles.add(userRole);
	    }
	    userRoleRepository.save(userRoles);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return mapper.userToUserBean(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBean> getAllUsers() throws DBException {
	LOGGER.info("Find all active users");

	List<UserBean> users = null;
	try {
	    List<User> entities = userRepository.findAllByActive(Byte
		    .valueOf(UserInfo.ACTIVE.toString()));
	    users = mapper.listOfUsersToListOfUserBeans(entities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return users;
    }

    @Override
    @Transactional(readOnly = true)
    public UserBean getUserProfile(Long id) throws DBException {
	LOGGER.info("Finding user with id {}", id);

	return mapper.userToUserBean(userRepository.findOne(id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public void deleteUser(UserBean user) throws DBException {
	LOGGER.info("Deleting user");

	try {
	    User entity = mapper.userBeanToUser(user);
	    userRepository.delete(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public void deleteUserWithCompany(Long userId) throws DBException {
	LOGGER.info("Deleting user");
	try {
	    User entity = userRepository.findOne(userId);
	    LOGGER.info("User has assigned {} - Lazy loading helper", entity
		    .getUserRoles().size());
	    entity.setActive(Byte.valueOf(UserInfo.INACTIVE.toString()));
	    userRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional()
    public UserBean getActiveUserAndRoles(String username) throws DBException {
	LOGGER.info("Getting active user and its roles");
	User entity = null;
	try {
	    entity = userRepository.findByUsernameAndActive(username,
		    Byte.valueOf(UserInfo.ACTIVE.toString()));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
	UserBean user = mapper.userToUserBean(entity);
	LOGGER.info("Mapping entity to Bean " + user);

	return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.UserManagementService#changeUserPassword
     * (java.lang.Long, java.lang.String)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DBException.class })
    public Integer changeUserPassword(Long userId, String password)
	    throws GenericException {
	LOGGER.info("Updating password for user id {}", userId);

	if (userId == null) {
	    throw new NoUserFoundException(
		    env.getProperty("exception.no.user"), "No user id found",
		    "N/A");
	}

	Integer result = null;
	try {
	    result = userRepository.changePassword(userId, encrypt(password));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return result;
    }
}
