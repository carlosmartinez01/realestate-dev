package com.maverik.realestate.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maverik.realestate.constants.RealEstateConstants.Roles;
import com.maverik.realestate.domain.entity.Role;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.RoleMapper;
import com.maverik.realestate.repository.RoleRepository;
import com.maverik.realestate.view.bean.RoleBean;

@Service
public class RoleManagementServiceImpl implements RoleManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(RoleManagementServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public RoleBean insertRole(RoleBean role) throws DBException {
	LOGGER.info("insertRole({})", role);
	try {
	    Role entity = roleMapper.roleBeanToRole(role);
	    entity = roleRepository.save(entity);
	    role.setId(entity.getId());
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return role;
    }

    @Override
    public RoleBean updateRole(RoleBean role) throws DBException {
	LOGGER.info("updateRole()");
	return insertRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = DBException.class)
    public void deleteRole(RoleBean role) throws DBException {
	try {
	    Role entity = roleMapper.roleBeanToRole(role);
	    roleRepository.delete(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public RoleBean findByRole(Long id) throws DBException {
	RoleBean role = null;
	try {
	    Role entity = roleRepository.findOne(id);
	    role = roleMapper.roleToRoleBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return role;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RoleBean> findAllRoles() throws DBException {
	List<RoleBean> listRoles = null;
	try {
	    List<Role> listEntities = roleRepository.findAll();
	    listRoles = roleMapper.rolesToRoleBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listRoles;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleBean> findAllRolesNotDefault(String roleName)
	    throws DBException {
	List<RoleBean> listRoles = null;
	try {
	    List<Role> listEntities = roleRepository
		    .findAllAndByRoleNameNot(roleName);
	    listRoles = roleMapper.rolesToRoleBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listRoles;
    }

    @Override
    public String getUserRoleSelected(Set<String> roles) {
	String role = Roles.USER.toString();
	if (roles != null && !roles.isEmpty()) {
	    for (String r : roles) {
		if (!r.equalsIgnoreCase(Roles.USER.toString())) {
		    role = r;
		}
	    }
	} else {
	    return null;
	}

	return role;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.RoleManagementService#findByRoleName(java
     * .lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public RoleBean findByRoleName(String name) throws GenericException {
	RoleBean role = null;
	try {
	    Role entity = roleRepository.findByRoleName(name);
	    role = roleMapper.roleToRoleBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return role;
    }
}
