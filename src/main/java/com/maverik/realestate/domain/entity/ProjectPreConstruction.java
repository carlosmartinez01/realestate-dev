/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
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

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "project_preconstruction")
public class ProjectPreConstruction implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -6493227320879775544L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private Project project;

    @Column(name = "constructionDocumentType", nullable = false, length = 400)
    private String constructionDocumentType;

    @Column(name = "checked", nullable = true, length = 10)
    private String checked;

    @Column(name = "contactName")
    private String contactName;

    @Column(name = "readyForPickUp")
    private String readyForPickUp;

    @Column(name = "dateReceived")
    private Date dateReceived;

    @Column(name = "permitFee")
    private String permitFee;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "permitFilename_id")
    private Filename permitFilename;

    @OneToMany(mappedBy = "preConstructionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PreConstructionDetails> details;

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

    public String getConstructionDocumentType() {
	return constructionDocumentType;
    }

    public void setConstructionDocumentType(String constructionDocumentType) {
	this.constructionDocumentType = constructionDocumentType;
    }

    @Override
    public String toString() {
	return "ProjectPreConstruction [id=" + id + ", project=" + project
		+ ", constructionDocumentType=" + constructionDocumentType
		+ "]";
    }

    public List<PreConstructionDetails> getDetails() {
	return details;
    }

    public void setDetails(List<PreConstructionDetails> details) {
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

    public String getPermitFee() {
	return permitFee;
    }

    public void setPermitFee(String permitFee) {
	this.permitFee = permitFee;
    }

    public Filename getPermitFilename() {
	return permitFilename;
    }

    public void setPermitFilename(Filename permitFilename) {
	this.permitFilename = permitFilename;
    }
}
