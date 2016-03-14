/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.ProjectRFI;
import com.maverik.realestate.view.bean.ProjectRFIBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PreConstructionSpecialMapper.class,
	PropertySpecialMapper.class })
public interface ProjectRFIMapper {

    @Mapping(source = "project", target = "project", ignore = true)
    ProjectRFI beanToEntity(ProjectRFIBean bean);

    ProjectRFIBean entityToBean(ProjectRFI entity);

    List<ProjectRFI> beansToEntities(List<ProjectRFIBean> beans);

    List<ProjectRFIBean> entitiesToBeans(List<ProjectRFI> entities);

}
