package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.ProjectManagement;
import com.maverik.realestate.view.bean.ProjectManagementBean;

@Mapper(componentModel = "spring",
    uses = {PreConstructionSpecialMapper.class, PropertySpecialMapper.class})
public interface ProjectManagementMapper {

  @Mapping(source = "project", target = "project", ignore = true)
  ProjectManagement beanToEntity(ProjectManagementBean bean);

  ProjectManagementBean entityToBean(ProjectManagement entity);

  List<ProjectManagement> beansToEntities(List<ProjectManagementBean> beans);

  List<ProjectManagementBean> entitiesToBeans(List<ProjectManagement> entities);
}
