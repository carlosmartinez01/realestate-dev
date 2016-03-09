/**
 * 
 */
package com.maverik.realestate.service;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.PageBean;

/**
 * @author jorge
 *
 */
public interface PageManagementService {

    public PageBean findPage(Long id) throws GenericException;

    public PageBean findByPageName(String name) throws GenericException;

}
