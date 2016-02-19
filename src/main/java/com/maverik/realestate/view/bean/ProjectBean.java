package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
public class ProjectBean implements Serializable {

    private static final long serialVersionUID = -279497662319827246L;

    private Long id;

    @NotEmpty(message = "Project name must not be empty")
    private String projectName;

    private String description;

    private Byte status;

    private String projectType;

    private PropertyBean property;

    private UserBean userId;

    public ProjectBean() {

    }

    /**
     * @param id
     * @param projectName
     * @param description
     * @param status
     * @param projectType
     * @param property
     * @param userId
     */
    public ProjectBean(Long id, String projectName, String description,
	    Byte status, String projectType, PropertyBean property,
	    UserBean userId) {
	super();
	this.id = id;
	this.projectName = projectName;
	this.description = description;
	this.status = status;
	this.projectType = projectType;
	this.property = property;
	this.userId = userId;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getProjectName() {
	return projectName;
    }

    public void setProjectName(String projectName) {
	this.projectName = projectName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Byte getStatus() {
	return status;
    }

    public void setStatus(Byte status) {
	this.status = status;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.projectName);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ProjectBean other = (ProjectBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.projectName, other.projectName);
    }

    @Override
    public String toString() {
	return "ProjectBean [id=" + id + ", projectName=" + projectName + "]";
    }

    public UserBean getUserId() {
	return userId;
    }

    public void setUserId(UserBean userId) {
	this.userId = userId;
    }

    public String getProjectType() {
	return projectType;
    }

    public void setProjectType(String projectType) {
	this.projectType = projectType;
    }

    public PropertyBean getProperty() {
	return property;
    }

    public void setProperty(PropertyBean property) {
	this.property = property;
    }
}
