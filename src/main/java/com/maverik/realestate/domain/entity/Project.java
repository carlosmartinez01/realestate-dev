package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@Entity
@Table(name = "project")
@SonarClassExclusion
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "PROJECTNAME", nullable = false, length = 256)
    private String projectName;

    @Column(name = "DESCRIPTION", nullable = false, length = 256)
    private String description;

    @Column(name = "STATUS", nullable = false)
    private Byte status;

    @Column(name = "projectType", nullable = true, length = 400)
    private String projectType;

    @OneToMany(mappedBy = "projectId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProjectNotes> projectNotes = new HashSet<ProjectNotes>();

    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProjectPreConstruction preConstruction;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "project_user_xref", joinColumns = { @JoinColumn(name = "projectId") }, inverseJoinColumns = { @JoinColumn(name = "userId") })
    private Set<User> users = new HashSet<User>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "PROPERTYID", nullable = true)
    private Property property;

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
	final Project other = (Project) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.projectName, other.projectName);
    }

    public Set<User> getUsers() {
	return users;
    }

    public void setUsers(Set<User> users) {
	this.users = users;
    }

    public Property getProperty() {
	return property;
    }

    public void setProperty(Property property) {
	this.property = property;
    }

    public Set<ProjectNotes> getProjectNotes() {
	return projectNotes;
    }

    public void setProjectNotes(Set<ProjectNotes> projectNotes) {
	this.projectNotes = projectNotes;
    }

    public String getProjectType() {
	return projectType;
    }

    public void setProjectType(String projectType) {
	this.projectType = projectType;
    }

    @Override
    public String toString() {
	return "Project [id=" + id + ", projectName=" + projectName + "]";
    }

    public ProjectPreConstruction getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(ProjectPreConstruction preConstruction) {
	this.preConstruction = preConstruction;
    }

}
