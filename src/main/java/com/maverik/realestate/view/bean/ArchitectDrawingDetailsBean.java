/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class ArchitectDrawingDetailsBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 3281240325005207938L;

    private Long id;

    private Long architectDrawingId;

    private String drawingDate;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getArchitectDrawingId() {
	return architectDrawingId;
    }

    public void setArchitectDrawingId(Long architectDrawingId) {
	this.architectDrawingId = architectDrawingId;
    }

    public String getDrawingDate() {
	return drawingDate;
    }

    public void setDrawingDate(String drawingDate) {
	this.drawingDate = drawingDate;
    }

    @Override
    public String toString() {
	return "ArchitectDrawingDetailsBean [id=" + id
		+ ", architectDrawingId=" + architectDrawingId
		+ ", drawingDate=" + drawingDate + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.architectDrawingId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ArchitectDrawingDetailsBean other = (ArchitectDrawingDetailsBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.architectDrawingId,
			other.architectDrawingId);
    }

}
