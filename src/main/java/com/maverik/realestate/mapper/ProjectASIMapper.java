/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.ProjectASI;
import com.maverik.realestate.view.bean.ProjectASIBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PreConstructionSpecialMapper.class,
	PropertySpecialMapper.class })
public interface ProjectASIMapper {

    @Mapping(source = "project", target = "project", ignore = true)
    ProjectASI beanToEntity(ProjectASIBean bean);

    ProjectASIBean entityToBean(ProjectASI entity);

    List<ProjectASI> beansToEntities(List<ProjectASIBean> beans);

    List<ProjectASIBean> entitiesToBeans(List<ProjectASI> entities);
}
