/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.PurchaseExtension;
import com.maverik.realestate.view.bean.PurchaseExtensionBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PurchaseSpecialMapper.class })
public interface PurchaseExtensionMapper {

    PurchaseExtension purchaseExtensionBeanToPurchaseExtension(
	    PurchaseExtensionBean purchaseExtensionBean);

    PurchaseExtensionBean purchaseExtensionToPurchaseExtensionBean(
	    PurchaseExtension purchaseExtension);

    List<PurchaseExtension> purchaseExtensionBeansToPurchaseExtensions(
	    List<PurchaseExtensionBean> purchaseExtensionBeans);

    List<PurchaseExtensionBean> purchaseExtensionsToPurchaseExtensionBeans(
	    List<PurchaseExtension> purchaseExtensions);

}
