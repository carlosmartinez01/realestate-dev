/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class ArchitectDrawingBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -8425778753420006189L;

    private Long id;

    private Long preConstructionId;

    private List<ArchitectDrawingDetailsBean> drawingDetails = new ArrayList<ArchitectDrawingDetailsBean>();

    private String type;

    private String completed;

    public void addDrawingDetailsBean(ArchitectDrawingDetailsBean drawing) {
	drawingDetails.add(drawing);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public List<ArchitectDrawingDetailsBean> getDrawingDetails() {
	return drawingDetails;
    }

    public void setDrawingDetails(
	    List<ArchitectDrawingDetailsBean> drawingDetails) {
	this.drawingDetails = drawingDetails;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getCompleted() {
	return completed;
    }

    public void setCompleted(String completed) {
	this.completed = completed;
    }

    @Override
    public String toString() {
	return "ArchitectDrawingBean [id=" + id + ", preConstructionId="
		+ preConstructionId + ", drawingDetails=" + drawingDetails
		+ ", type=" + type + ", completed=" + completed + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.preConstructionId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ArchitectDrawingBean other = (ArchitectDrawingBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstructionId,
			other.preConstructionId);
    }

    public Long getPreConstructionId() {
	return preConstructionId;
    }

    public void setPreConstructionId(Long preConstructionId) {
	this.preConstructionId = preConstructionId;
    }

}
