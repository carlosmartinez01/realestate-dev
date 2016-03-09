/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PermittingContact;
import com.maverik.realestate.view.bean.PermittingContactBean;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Mapper(componentModel = "spring", uses = { PropertySpecialMapper.class })
public interface PermittingContactMapper {

    PermittingContact beanToEntity(PermittingContactBean bean);

    @Mapping(source = "propertyId", target = "propertyId", ignore = true)
    PermittingContactBean entityToBean(PermittingContact entity);

    List<PermittingContact> beansToEntities(List<PermittingContactBean> beans);

    List<PermittingContactBean> entitiesToBeans(List<PermittingContact> entities);
}
