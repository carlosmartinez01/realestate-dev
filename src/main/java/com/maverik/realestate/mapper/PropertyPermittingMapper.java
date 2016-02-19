/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PropertyPermitting;
import com.maverik.realestate.view.bean.PropertyPermittingBean;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Mapper(componentModel = "spring", uses = { PropertySpecialMapper.class })
public interface PropertyPermittingMapper {

    PropertyPermitting propertyPermittingBeanToPropertyPermitting(
	    PropertyPermittingBean propertyPermittingBean);

    PropertyPermittingBean propertyPermittingToPropertyPermittingBean(
	    PropertyPermitting propertyPermitting);

    List<PropertyPermitting> listOfPropertyPermittingBeansToListOfPropertiesPermitting(
	    List<PropertyPermittingBean> propertyPermittingBeans);

    List<PropertyPermittingBean> listOfPropertiesPermittingToListOfPropertyPermittingBeans(
	    List<PropertyPermitting> propertiesPermitting);

}
