/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "architect_drawings")
public class ArchitectDrawing implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -6533463702758522449L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "preConstructionId", nullable = false)
    private ProjectPreConstruction preConstruction;

    @OneToMany(mappedBy = "architectDrawing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArchitectDrawingDetails> drawingDetails = new ArrayList<ArchitectDrawingDetails>();

    @Column(name = "type")
    private String type;

    @Column(name = "completed")
    private String completed;

    public void addDrawingDetails(ArchitectDrawingDetails drawing) {
	drawingDetails.add(drawing);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public ProjectPreConstruction getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(ProjectPreConstruction preConstruction) {
	this.preConstruction = preConstruction;
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
	return "ArchitectDrawing [id=" + id + ", preConstruction="
		+ preConstruction + ", type=" + type + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.preConstruction);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ArchitectDrawing other = (ArchitectDrawing) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstruction, other.preConstruction);
    }

    public List<ArchitectDrawingDetails> getDrawingDetails() {
	return drawingDetails;
    }

    public void setDrawingDetails(List<ArchitectDrawingDetails> drawingDetails) {
	this.drawingDetails = drawingDetails;
    }

}
