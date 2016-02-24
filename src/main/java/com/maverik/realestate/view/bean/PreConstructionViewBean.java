/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.util.List;

/**
 * @author jorge
 *
 */
public class PreConstructionViewBean {

    private Long projectId;

    private List<ProjectPreConstructionBean> preConstruction;

    public List<ProjectPreConstructionBean> getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(
	    List<ProjectPreConstructionBean> preConstruction) {
	this.preConstruction = preConstruction;
    }

    public Long getProjectId() {
	return projectId;
    }

    public void setProjectId(Long projectId) {
	this.projectId = projectId;
    }
}
