/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "project_preconstruction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class ProjectPreConstruction implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -6493227320879775544L;

    @Transient
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private Project project;

    @Column(name = "readyForPickUp")
    private String readyForPickUp;

    @Column(name = "dateReceived")
    private Date dateReceived;

    @Transient
    private String dateReceivedAsString;

    @Column(name = "permitFee")
    private BigDecimal permitFee;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "permitFilename_id")
    private Filename permitFilename;

    @OneToMany(mappedBy = "preConstruction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PreConstructionType> details = new ArrayList<PreConstructionType>();

    @OneToMany(mappedBy = "preConstruction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArchitectDrawing> drawings = new ArrayList<ArchitectDrawing>();

    public void addTypes(PreConstructionType e) {
	details.add(e);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Project getProject() {
	return project;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.project.getId());
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ProjectPreConstruction other = (ProjectPreConstruction) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project.getId(), other.project.getId());
    }

    @Override
    public String toString() {
	return "ProjectPreConstruction [id=" + id + ", project=" + project
		+ "]";
    }

    public List<PreConstructionType> getDetails() {
	return details;
    }

    public void setDetails(List<PreConstructionType> details) {
	this.details = details;
    }

    public String getReadyForPickUp() {
	return readyForPickUp;
    }

    public void setReadyForPickUp(String readyForPickUp) {
	this.readyForPickUp = readyForPickUp;
    }

    public Date getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
	this.dateReceived = dateReceived;
    }

    public BigDecimal getPermitFee() {
	return permitFee;
    }

    public void setPermitFee(BigDecimal permitFee) {
	this.permitFee = permitFee;
    }

    public Filename getPermitFilename() {
	return permitFilename;
    }

    public void setPermitFilename(Filename permitFilename) {
	this.permitFilename = permitFilename;
    }

    public String getDateReceivedAsString() {
	return dateReceivedAsString;
    }

    public void setDateReceivedAsString(String dateReceivedAsString)
	    throws DateTimeParseException {
	this.dateReceivedAsString = dateReceivedAsString;
	if (dateReceivedAsString != null) {
	    LocalDate date = LocalDate.parse(dateReceivedAsString, dtf);
	    this.dateReceived = new Date(date.toEpochDay());
	}
    }

    public List<ArchitectDrawing> getDrawings() {
	return drawings;
    }

    public void setDrawings(List<ArchitectDrawing> drawings) {
	this.drawings = drawings;
    }
}
