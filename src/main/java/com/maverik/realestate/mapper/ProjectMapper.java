package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.view.bean.ProjectBean;

@Mapper(componentModel = "spring", uses = { ProjectSpecialMapper.class })
public interface ProjectMapper {

    Project projectBeanToProject(ProjectBean projectBean);

    ProjectBean projectToProjectBean(Project project);

    List<Project> listOfProjectBeansToListOfProjects(
	    List<ProjectBean> projectBeans);

    List<ProjectBean> listOfProjectToListOfProjectBeans(List<Project> projects);
}
