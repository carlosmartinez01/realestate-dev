/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class ProjectPreConstructionBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -695024636009038476L;

    private Long id;

    private Long project;

    private String constructionDocumentType;

    private String checked;

    private String contactName;

    private String readyForPickUp;

    private String dateReceived;

    private String permitFee;

    private FileBean permitFilename;

    private List<PreConstructionDetailsBean> details;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getProject() {
	return project;
    }

    public void setProject(Long project) {
	this.project = project;
    }

    public String getConstructionDocumentType() {
	return constructionDocumentType;
    }

    public void setConstructionDocumentType(String constructionDocumentType) {
	this.constructionDocumentType = constructionDocumentType;
    }

    public String getChecked() {
	return checked;
    }

    public void setChecked(String checked) {
	this.checked = checked;
    }

    public String getContactName() {
	return contactName;
    }

    public void setContactName(String contactName) {
	this.contactName = contactName;
    }

    @Override
    public String toString() {
	return "ProjectPreConstructionBean [id=" + id + ", project=" + project
		+ ", constructionDocumentType=" + constructionDocumentType
		+ "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.project);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ProjectPreConstructionBean other = (ProjectPreConstructionBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project, other.project);
    }

    public String getReadyForPickUp() {
	return readyForPickUp;
    }

    public void setReadyForPickUp(String readyForPickUp) {
	this.readyForPickUp = readyForPickUp;
    }

    public String getPermitFee() {
	return permitFee;
    }

    public void setPermitFee(String permitFee) {
	this.permitFee = permitFee;
    }

    public FileBean getPermitFilename() {
	return permitFilename;
    }

    public void setPermitFilename(FileBean permitFilename) {
	this.permitFilename = permitFilename;
    }

    public String getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
	this.dateReceived = dateReceived;
    }

    public List<PreConstructionDetailsBean> getDetails() {
	return details;
    }

    public void setDetails(List<PreConstructionDetailsBean> details) {
	this.details = details;
    }
}
