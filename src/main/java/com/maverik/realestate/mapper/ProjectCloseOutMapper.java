package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maverik.realestate.domain.entity.ProjectCloseOut;
import com.maverik.realestate.view.bean.ProjectCloseOutBean;

@Mapper(componentModel = "spring",
    uses = {PreConstructionSpecialMapper.class, PropertySpecialMapper.class})
public interface ProjectCloseOutMapper {

  @Mapping(source = "project", target = "project", ignore = true)
  ProjectCloseOut beanToEntity(ProjectCloseOutBean bean);

  ProjectCloseOutBean entityToBean(ProjectCloseOut entity);

  List<ProjectCloseOut> beansToEntities(List<ProjectCloseOutBean> beans);

  List<ProjectCloseOutBean> entitiesToBeans(List<ProjectCloseOut> entities);
}
