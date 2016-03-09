/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.PropertyLease;
import com.maverik.realestate.view.bean.PropertyLeaseBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PropertySpecialMapper.class })
public interface PropertyLeaseMapper {

    PropertyLease propertyLeaseBeanToPropertyLease(
	    PropertyLeaseBean propertyLeaseBean);

    PropertyLeaseBean propertyLeaseToPropertyLeaseBean(
	    PropertyLease propertyLease);

    List<PropertyLease> listOfPropertyLeaseBeansToListOfPropertiesLease(
	    List<PropertyLeaseBean> propertyLeaseBeans);

    List<PropertyLeaseBean> listOfPropertiesLeaseToListOfPropertyLeaseBeans(
	    List<PropertyLease> propertiesLease);

}
