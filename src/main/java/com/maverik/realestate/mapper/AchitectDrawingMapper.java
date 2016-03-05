/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.ProjectPreConstruction;
import com.maverik.realestate.view.bean.ProjectPreConstructionBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PreConstructionSpecialMapper.class,
	PropertySpecialMapper.class })
public interface AchitectDrawingMapper {

    @Mapping(source = "project", target = "project", ignore = true)
    ProjectPreConstruction beanToEntity(ProjectPreConstructionBean bean);

    ProjectPreConstructionBean entityToBean(ProjectPreConstruction entity);

    List<ProjectPreConstruction> beansToEntities(
	    List<ProjectPreConstructionBean> beans);

    List<ProjectPreConstructionBean> entitiesToBeans(
	    List<ProjectPreConstruction> entities);
}
