package com.maverik.realestate.service;

import java.util.List;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.PreConstructionViewBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.PropertyBean;

public interface ProjectManagementService {

    public ProjectBean insertProject(ProjectBean project)
	    throws GenericException;

    public ProjectBean updateProject(ProjectBean project)
	    throws GenericException;

    public void deleteProject(ProjectBean project) throws GenericException;

    public ProjectBean findByProject(Long id) throws GenericException;

    public List<ProjectBean> findAllProjects() throws GenericException;

    public List<ProjectBean> findProjectsByResearchPhase()
	    throws GenericException;

    public ProjectBean findByProjectName(String projectName)
	    throws GenericException;

    public ProjectBean addUsersAssignedToProject(Long projectId,
	    List<Long> users) throws GenericException;

    public PreConstructionViewBean getPreConstruction(Long preConstructionId)
	    throws GenericException;

    public List<ProjectBean> getProjectsByProperty(Long propertyId)
	    throws GenericException;

    public void createNextProjectPhases(PropertyBean property,
	    ProjectBean project) throws GenericException;

    public PreConstructionViewBean savePreConstruction(
	    PreConstructionViewBean bean) throws GenericException;
}
