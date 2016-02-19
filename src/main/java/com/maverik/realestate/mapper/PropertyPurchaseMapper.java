/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.PropertyPurchase;
import com.maverik.realestate.view.bean.PropertyPurchaseBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PropertySpecialMapper.class })
public interface PropertyPurchaseMapper {

    PropertyPurchase propertyPurchaseBeanToPropertyPurchase(
	    PropertyPurchaseBean propertyPurchaseBean);

    PropertyPurchaseBean propertyPurchaseToPropertyPurchaseBean(
	    PropertyPurchase propertyPurchase);

    List<PropertyPurchase> listOfPropertyPurchaseBeansToListOfPropertiesPurchase(
	    List<PropertyPurchaseBean> propertyPurchaseBeans);

    List<PropertyPurchaseBean> listOfPropertiesPurchaseToListOfPropertyPurchaseBeans(
	    List<PropertyPurchase> propertiesPurchase);
}
