/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.LeaseExtension;
import com.maverik.realestate.view.bean.LeaseExtensionBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { LeaseSpecialMapper.class })
public interface LeaseExtensionMapper {

    LeaseExtension leaseExtensionBeanToLeaseExtension(
	    LeaseExtensionBean leaseExtensionBean);

    LeaseExtensionBean leaseExtensionToLeaseExtensionBean(
	    LeaseExtension leaseExtension);

    List<LeaseExtension> leaseExtensionBeansToLeaseExtensions(
	    List<LeaseExtensionBean> leaseExtensionBeans);

    List<LeaseExtensionBean> leaseExtensionsToLeaseExtensionBeans(
	    List<LeaseExtension> leaseExtensions);

}
