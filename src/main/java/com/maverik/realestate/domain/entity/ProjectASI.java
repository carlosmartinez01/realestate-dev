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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author jorge
 *
 */
@Entity
@Table(name = "project_asi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class ProjectASI implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 5067980822283778516L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "asiFile")
    private Filename asiFile;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the project
     */
    public Project getProject() {
	return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(Project project) {
	this.project = project;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
	return notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes(String notes) {
	this.notes = notes;
    }

    /**
     * @return the asiFile
     */
    public Filename getAsiFile() {
	return asiFile;
    }

    /**
     * @param asiFile
     *            the asiFile to set
     */
    public void setAsiFile(Filename asiFile) {
	this.asiFile = asiFile;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProjectASI [id=" + id + ", project=" + project
		+ ", description=" + description + ", notes=" + notes
		+ ", asiFile=" + asiFile + "]";
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
	final ProjectASI other = (ProjectASI) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project, other.project);
    }

    /**
     * @return the creationTime
     */
    public Date getCreationTime() {
	return creationTime;
    }

    /**
     * @param creationTime
     *            the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }
}
