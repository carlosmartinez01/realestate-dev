/**
 * 
 */
package com.maverik.realestate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maverik.realestate.exception.DBException;
import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.handler.ExceptionHandler;
import com.maverik.realestate.mapper.PageMapper;
import com.maverik.realestate.repository.PageRepository;
import com.maverik.realestate.view.bean.PageBean;

/**
 * @author jorge
 *
 */
@Service
public class PageManagementServiceImpl implements PageManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(PageManagementServiceImpl.class);

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.PageManagementService#findPage()
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PageBean findPage(Long id) throws GenericException {
	LOGGER.info("Find page {}", id);

	try {
	    return pageMapper.pageToPageBean(pageRepository.findOne(id));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.PageManagementService#findByPageName(java
     * .lang.String)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	    GenericException.class, DataAccessException.class,
	    DBException.class })
    public PageBean findByPageName(String name) throws GenericException {
	LOGGER.info("Find by name {}", name);

	try {
	    return pageMapper.pageToPageBean(pageRepository
		    .findByPageName(name));
	} catch (DataAccessException ex) {
	    LOGGER.debug(ex.getMessage());
	    LOGGER.info(ex.getMostSpecificCause().toString());
	    throw exceptionHandler.getException(ex);
	}
    }

}
