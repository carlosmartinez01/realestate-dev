/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

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

    private String readyForPickUp;

    @NotEmpty(message = "Date is mandatory field")
    private String dateReceived;

    private BigDecimal permitFee;

    private FileBean permitFilename;

    @Valid
    private List<PreConstructionTypeBean> details = new ArrayList<PreConstructionTypeBean>();

    @Valid
    private List<ArchitectDrawingBean> drawings = new ArrayList<ArchitectDrawingBean>();

    @Override
    public String toString() {
	return "ProjectPreConstructionBean [id=" + id + ", project=" + project
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

    public String getReadyForPickUp() {
	return readyForPickUp;
    }

    public void setReadyForPickUp(String readyForPickUp) {
	this.readyForPickUp = readyForPickUp;
    }

    public String getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
	this.dateReceived = dateReceived;
    }

    public BigDecimal getPermitFee() {
	return permitFee;
    }

    public void setPermitFee(BigDecimal permitFee) {
	this.permitFee = permitFee;
    }

    public FileBean getPermitFilename() {
	return permitFilename;
    }

    public void setPermitFilename(FileBean permitFilename) {
	this.permitFilename = permitFilename;
    }

    public List<PreConstructionTypeBean> getDetails() {
	return details;
    }

    public void setDetails(List<PreConstructionTypeBean> details) {
	this.details = details;
    }

    public List<ArchitectDrawingBean> getDrawings() {
	return drawings;
    }

    public void setDrawings(List<ArchitectDrawingBean> drawings) {
	this.drawings = drawings;
    }
}
