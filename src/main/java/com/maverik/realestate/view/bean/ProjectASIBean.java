/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author jorge
 *
 */
public class ProjectASIBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8465653295633461961L;

    private Long id;

    private Long project;

    private String description;

    private String notes;

    private FileBean asiFile;

    private List<String> people;

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
    public FileBean getAsiFile() {
	return asiFile;
    }

    /**
     * @param asiFile
     *            the asiFile to set
     */
    public void setAsiFile(FileBean asiFile) {
	this.asiFile = asiFile;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProjectASIBean [id=" + id + ", project=" + project
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
	final ProjectASIBean other = (ProjectASIBean) obj;
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

    /**
     * @return the people
     */
    public List<String> getPeople() {
	return people;
    }

    /**
     * @param people
     *            the people to set
     */
    public void setPeople(List<String> people) {
	this.people = people;
    }
}
