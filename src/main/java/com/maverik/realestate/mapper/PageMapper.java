/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.Page;
import com.maverik.realestate.view.bean.PageBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring")
public interface PageMapper {

    Page pageBeanToPage(PageBean pageBean);

    PageBean pageToPageBean(Page page);

    List<Page> listOfPageBeansToListOfPage(List<PageBean> pageBeans);

    List<PageBean> listOfPageToListOfPageBeans(List<Page> pages);

}
