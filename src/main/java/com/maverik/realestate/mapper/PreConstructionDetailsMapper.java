/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.PreConstructionDetails;
import com.maverik.realestate.view.bean.PreConstructionDetailsBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PreConstructionDetailsSpecialMapper.class })
public interface PreConstructionDetailsMapper {

    @Mapping(source = "preConstructionId", target = "preConstructionId", ignore = true)
    PreConstructionDetails beanDetailsToEntityDetails(
	    PreConstructionDetailsBean bean);

    PreConstructionDetailsBean entityDetailsToBeanDetails(
	    PreConstructionDetails entity);

    List<PreConstructionDetails> beansDetailsToEntitiesDetails(
	    List<PreConstructionDetailsBean> beans);

    List<PreConstructionDetailsBean> entitiesDetailsToBeansDetails(
	    List<PreConstructionDetails> entities);

}
