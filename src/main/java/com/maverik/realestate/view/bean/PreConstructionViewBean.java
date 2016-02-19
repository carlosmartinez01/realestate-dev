/**
 * 
 */
package com.maverik.realestate.view.bean;

import com.maverik.realestate.domain.entity.PreConstructionDetails;

/**
 * @author jorge
 *
 */
public class PreConstructionViewBean {

    private ProjectPreConstructionBean preConstruction;

    private PreConstructionDetails preDetails;

    public ProjectPreConstructionBean getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(ProjectPreConstructionBean preConstruction) {
	this.preConstruction = preConstruction;
    }

    public PreConstructionDetails getPreDetails() {
	return preDetails;
    }

    public void setPreDetails(PreConstructionDetails preDetails) {
	this.preDetails = preDetails;
    }

}
