/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PermittingAssignmentTask;
import com.maverik.realestate.view.bean.PermittingAssignmentTaskBean;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Mapper(componentModel = "spring", uses = { PropertySpecialMapper.class })
public interface PermittingTaskMapper {

    PermittingAssignmentTask beanToEntity(PermittingAssignmentTaskBean bean);

    @Mapping(source = "propertyId", target = "propertyId", ignore = true)
    PermittingAssignmentTaskBean entityToBean(PermittingAssignmentTask entity);

    List<PermittingAssignmentTask> beansToEntities(
	    List<PermittingAssignmentTaskBean> beans);

    List<PermittingAssignmentTaskBean> entitiesToBeans(
	    List<PermittingAssignmentTask> entities);

}
