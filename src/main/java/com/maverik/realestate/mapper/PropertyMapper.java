package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.view.bean.PropertyBean;

@Mapper(componentModel = "spring", uses = { PropertyUserMapper.class,
	PropertySpecialMapper.class })
public interface PropertyMapper {

    Property propertyBeanToProperty(PropertyBean propertyBean);

    PropertyBean propertyToPropertyBean(Property property);

    List<Property> listOfPropertyBeansToListOfProperties(
	    List<PropertyBean> propertyBeans);

    List<PropertyBean> listOfPropertiesToListOfPropertyBeans(
	    List<Property> properties);

}
