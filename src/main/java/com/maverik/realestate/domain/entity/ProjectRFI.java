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

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "project_rfi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class ProjectRFI implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7720697560229436585L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @Column(name = "sentTo")
    private String sentTo;

    @Column(name = "dateRequiredBy")
    private Date dateRequiredBy;

    @Column(name = "subject")
    private String subject;

    @Column(name = "discipline")
    private String discipline;

    @Column(name = "question")
    private String question;

    @Column(name = "suggestion")
    private String suggestion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rfiFile")
    private Filename rfiFile;

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
     * @return the sentTo
     */
    public String getSentTo() {
	return sentTo;
    }

    /**
     * @param sentTo
     *            the sentTo to set
     */
    public void setSentTo(String sentTo) {
	this.sentTo = sentTo;
    }

    /**
     * @return the dateRequiredBy
     */
    public Date getDateRequiredBy() {
	return dateRequiredBy;
    }

    /**
     * @param dateRequiredBy
     *            the dateRequiredBy to set
     */
    public void setDateRequiredBy(Date dateRequiredBy) {
	this.dateRequiredBy = dateRequiredBy;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
	return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
	this.subject = subject;
    }

    /**
     * @return the discipline
     */
    public String getDiscipline() {
	return discipline;
    }

    /**
     * @param discipline
     *            the discipline to set
     */
    public void setDiscipline(String discipline) {
	this.discipline = discipline;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
	return question;
    }

    /**
     * @param question
     *            the question to set
     */
    public void setQuestion(String question) {
	this.question = question;
    }

    /**
     * @return the suggestion
     */
    public String getSuggestion() {
	return suggestion;
    }

    /**
     * @param suggestion
     *            the suggestion to set
     */
    public void setSuggestion(String suggestion) {
	this.suggestion = suggestion;
    }

    /**
     * @return the rfiFile
     */
    public Filename getRfiFile() {
	return rfiFile;
    }

    /**
     * @param rfiFile
     *            the rfiFile to set
     */
    public void setRfiFile(Filename rfiFile) {
	this.rfiFile = rfiFile;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProjectRFI [id=" + id + ", project=" + project + ", sentTo="
		+ sentTo + ", dateRequiredBy=" + dateRequiredBy + ", subject="
		+ subject + ", discipline=" + discipline + ", question="
		+ question + ", suggestion=" + suggestion + ", rfiFile="
		+ rfiFile + "]";
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
	final ProjectRFI other = (ProjectRFI) obj;
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
