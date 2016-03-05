/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "architect_drawings_details")
public class ArchitectDrawingDetails implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -7140912871228493380L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "architectDrawingsId", nullable = false)
    private ArchitectDrawing architectDrawing;

    @Column(name = "drawingDate")
    private Date drawingDate;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public ArchitectDrawing getArchitectDrawing() {
	return architectDrawing;
    }

    public void setArchitectDrawing(ArchitectDrawing architectDrawing) {
	this.architectDrawing = architectDrawing;
    }

    public Date getDrawingDate() {
	return drawingDate;
    }

    public void setDrawingDate(Date drawingDate) {
	this.drawingDate = drawingDate;
    }

    @Override
    public String toString() {
	return "ArchitectDrawingDetails [id=" + id + ", architectDrawing="
		+ architectDrawing + ", drawingDate=" + drawingDate + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.architectDrawing);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ArchitectDrawingDetails other = (ArchitectDrawingDetails) obj;
	return Objects.equals(this.id, other.id)
		&& Objects
			.equals(this.architectDrawing, other.architectDrawing);
    }

}
