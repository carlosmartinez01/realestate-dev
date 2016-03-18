package com.maverik.realestate.service;

import java.util.List;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.view.bean.ArchitectDrawingBean;
import com.maverik.realestate.view.bean.FileBean;
import com.maverik.realestate.view.bean.ProjectASIBean;
import com.maverik.realestate.view.bean.ProjectBean;
import com.maverik.realestate.view.bean.ProjectCloseOutBean;
import com.maverik.realestate.view.bean.ProjectManagementBean;
import com.maverik.realestate.view.bean.ProjectPreConstructionBean;
import com.maverik.realestate.view.bean.ProjectRFIBean;
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

    public ProjectPreConstructionBean getPreConstruction(Long projectId)
	    throws GenericException;

    public List<ArchitectDrawingBean> getArchitectDrawing(Long preConstructionId)
	    throws GenericException;

    public void createNextProjectPhases(PropertyBean property,
	    ProjectBean project) throws GenericException;

    public ProjectPreConstructionBean savePreConstruction(
	    ProjectPreConstructionBean bean) throws GenericException;

    public FileBean addPreConstructionDetailFile(FileBean fileBean,
	    Long preConstructionDetailId) throws GenericException;

    public FileBean addPreConstructionPermitFile(FileBean fileBean,
	    Long preConstructionId) throws GenericException;

    public List<ArchitectDrawingBean> saveDrawingDetails(
	    ProjectPreConstructionBean bean) throws GenericException;

    public ProjectManagementBean getProjectManagement(Long projectId)
	    throws GenericException;

    public ProjectManagementBean saveManagement(ProjectManagementBean bean)
	    throws GenericException;

    public ProjectManagementBean moveToCloseOut(ProjectManagementBean bean)
	    throws GenericException;

    public ProjectCloseOutBean getProjectCloseOut(Long projectId)
	    throws GenericException;

    public ProjectCloseOutBean saveCloseOut(ProjectCloseOutBean bean)
	    throws GenericException;

    public ProjectBean closeProject(Long projectId) throws GenericException;

    public List<ProjectRFIBean> findRFIByProperty(Long propertyId)
	    throws GenericException;

    public ProjectRFIBean saveRFI(ProjectRFIBean bean) throws GenericException;

    public List<ProjectBean> getProjectsByProperty(Long propertyId)
	    throws GenericException;

    public List<ProjectASIBean> findASIByProperty(Long propertyId)
	    throws GenericException;

    public ProjectASIBean saveASI(ProjectASIBean bean) throws GenericException;

    public FileBean addRFIFile(FileBean fileBean, Long rfiId, Long projectId)
	    throws GenericException;

    public FileBean addASIFile(FileBean fileBean, Long asiId, Long projectId)
	    throws GenericException;

    public FileBean addConstructionBudgetFile(FileBean fileBean,
	    Long managementId, Long projectId) throws GenericException;

    public FileBean addRedlinesFile(FileBean fileBean, Long closeOutId,
	    Long projectId) throws GenericException;

    public FileBean addGeneralContractorFile(FileBean fileBean,
	    Long closeOutId, Long projectId) throws GenericException;

    public FileBean addPunchListItemsFile(FileBean fileBean, Long closeOutId,
	    Long projectId) throws GenericException;

}
