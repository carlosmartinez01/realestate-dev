/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.PropertyLOI;
import com.maverik.realestate.view.bean.PropertyLOIBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PropertySpecialMapper.class })
public interface PropertyLOIMapper {

    PropertyLOI propertyLOIBeanToPropertyLOI(PropertyLOIBean propertyLOIBean);

    PropertyLOIBean propertyLOIToPropertyLOIBean(PropertyLOI propertyLOI);

    List<PropertyLOI> listOfPropertyLOIBeansToListOfPropertiesLOI(
	    List<PropertyLOIBean> propertyLOIBeans);

    List<PropertyLOIBean> listOfPropertiesLOIToListOfPropertyLOIBeans(
	    List<PropertyLOI> propertiesLOI);

}
