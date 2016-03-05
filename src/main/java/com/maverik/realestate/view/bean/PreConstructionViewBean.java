/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.util.List;

import javax.validation.Valid;

import com.maverik.realestate.domain.entity.ProjectPreConstruction;

/**
 * @author jorge
 *
 */
public class PreConstructionViewBean {

    private Long projectId;

    @Valid
    private List<ProjectPreConstruction> preConstruction;

    public List<ProjectPreConstruction> getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(List<ProjectPreConstruction> preConstruction) {
	this.preConstruction = preConstruction;
    }

    public Long getProjectId() {
	return projectId;
    }

    public void setProjectId(Long projectId) {
	this.projectId = projectId;
    }
}
