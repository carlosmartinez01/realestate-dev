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

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.constants.RealEstateConstants.UserInfo;
import com.maverik.realestate.domain.entity.Company;
import com.maverik.realestate.domain.entity.User;
import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.CompanyAndUserMapper;
import com.maverik.realestate.mapper.CompanyMapper;
import com.maverik.realestate.mapper.UserMapper;
import com.maverik.realestate.repository.CompanyRepository;
import com.maverik.realestate.view.bean.CompanyBean;

@SonarClassExclusion
@Service
public class CompanyManagementServiceImpl implements CompanyManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(CompanyManagementServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyAndUserMapper companyAndUserMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private NoteManagementService noteManagementService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public CompanyBean insertCompany(CompanyBean company) throws DBException {
	LOGGER.info("insertCompany({})", company);
	Company entity = null;
	try {
	    entity = companyMapper.companyBeanToCompany(company);
	    entity.setActive(true);
	    entity = companyRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return companyMapper.companyToCompanyBean(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public CompanyBean updateCompany(CompanyBean company) throws DBException {
	LOGGER.info("updateCompany({})", company);

	try {
	    Company entity = companyMapper.companyBeanToCompany(company);
	    return companyMapper.companyToCompanyBean(companyRepository
		    .save(entity));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public void deleteCompany(CompanyBean company) throws DBException {
	try {
	    Company entity = companyMapper.companyBeanToCompany(company);
	    companyRepository.delete(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public void softDeleteCompany(Long id) throws DBException {
	try {
	    Company entity = companyRepository.findOne(id);
	    Set<User> users = entity.getUsers();
	    if (users != null && !users.isEmpty()) {
		for (User user : users) {
		    user.setActive(Byte.valueOf(UserInfo.INACTIVE.toString()));
		}
	    }
	    entity.setActive(false);
	    companyRepository.save(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyBean findByCompany(Long id) throws DBException {
	CompanyBean company = null;
	try {
	    Company entity = companyRepository.findOne(id);
	    company = companyAndUserMapper.companyToCompanyBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return company;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyBean> findAllCompanies() throws DBException {
	List<CompanyBean> listCompanies = null;
	try {
	    List<Company> listEntities = companyRepository.findAllCompanies();
	    listCompanies = companyMapper
		    .listOfCompanyToListOfCompanyBeans(listEntities);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return listCompanies;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.CompanyManagementService#findByCompanyName
     * (java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyBean findByCompanyName(String name) throws GenericException {
	CompanyBean company = null;
	try {
	    Company entity = companyRepository.findByCompanyName(name);
	    company = companyAndUserMapper.companyToCompanyBean(entity);
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}

	return company;
    }
}
