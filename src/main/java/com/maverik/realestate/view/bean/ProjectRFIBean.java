/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author jorge
 *
 */
public class ProjectRFIBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -218759367528238283L;

    private Long id;

    private Long project;

    private String sentTo;

    private String dateRequiredBy;

    private String subject;

    private String discipline;

    private String question;

    private String suggestion;

    private FileBean rfiFile;

    private String creationTime;

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
    public Long getProject() {
	return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(Long project) {
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
    public String getDateRequiredBy() {
	return dateRequiredBy;
    }

    /**
     * @param dateRequiredBy
     *            the dateRequiredBy to set
     */
    public void setDateRequiredBy(String dateRequiredBy) {
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
    public FileBean getRfiFile() {
	return rfiFile;
    }

    /**
     * @param rfiFile
     *            the rfiFile to set
     */
    public void setRfiFile(FileBean rfiFile) {
	this.rfiFile = rfiFile;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProjectRFIBean [id=" + id + ", project=" + project
		+ ", sentTo=" + sentTo + ", dateRequiredBy=" + dateRequiredBy
		+ ", subject=" + subject + ", discipline=" + discipline
		+ ", question=" + question + ", suggestion=" + suggestion
		+ ", rfiFile=" + rfiFile + "]";
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
	final ProjectRFIBean other = (ProjectRFIBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project, other.project);
    }

    /**
     * @return the creationTime
     */
    public String getCreationTime() {
	return creationTime;
    }

    /**
     * @param creationTime
     *            the creationTime to set
     */
    public void setCreationTime(String creationTime) {
	this.creationTime = creationTime;
    }

}
