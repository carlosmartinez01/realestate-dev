/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PropertyContract;
import com.maverik.realestate.view.bean.PropertyContractBean;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Mapper(componentModel = "spring", uses = { PropertySpecialMapper.class })
public interface PropertyContractMapper {

    PropertyContract propertyContractBeanToPropertyContract(
	    PropertyContractBean propertyContractBean);

    PropertyContractBean propertyContractToPropertyContractBean(
	    PropertyContract propertyContract);

    List<PropertyContract> listOfPropertyContractBeansToListOfPropertiesContract(
	    List<PropertyContractBean> propertyContractBeans);

    List<PropertyContractBean> listOfPropertiesContractToListOfPropertyContractBeans(
	    List<PropertyContract> propertiesContract);
}
