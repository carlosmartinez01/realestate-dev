package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "projectPhase", nullable = true, length = 400)
    private String projectPhase;

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

    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProjectManagement management;

    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProjectCloseOut closeOut;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectRFI> projectRFIs = new ArrayList<ProjectRFI>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectASI> projectASIs = new ArrayList<ProjectASI>();

    public void addProjectRFI(ProjectRFI rfi) {
	projectRFIs.add(rfi);
    }

    public void addProjectASI(ProjectASI asi) {
	projectASIs.add(asi);
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

    @Override
    public String toString() {
	return "Project [id=" + id + ", projectName=" + projectName + "]";
    }

    public String getProjectPhase() {
	return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
	this.projectPhase = projectPhase;
    }

    public ProjectPreConstruction getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(ProjectPreConstruction preConstruction) {
	this.preConstruction = preConstruction;
    }

    /**
     * @return the management
     */
    public ProjectManagement getManagement() {
	return management;
    }

    /**
     * @param management
     *            the management to set
     */
    public void setManagement(ProjectManagement management) {
	this.management = management;
    }

    /**
     * @return the closeOut
     */
    public ProjectCloseOut getCloseOut() {
	return closeOut;
    }

    /**
     * @param closeOut
     *            the closeOut to set
     */
    public void setCloseOut(ProjectCloseOut closeOut) {
	this.closeOut = closeOut;
    }

    /**
     * @return the projectRFIs
     */
    public List<ProjectRFI> getProjectRFIs() {
	return projectRFIs;
    }

    /**
     * @param projectRFIs
     *            the projectRFIs to set
     */
    public void setProjectRFIs(List<ProjectRFI> projectRFIs) {
	this.projectRFIs = projectRFIs;
    }

    /**
     * @return the projectASIs
     */
    public List<ProjectASI> getProjectASIs() {
	return projectASIs;
    }

    /**
     * @param projectASIs
     *            the projectASIs to set
     */
    public void setProjectASIs(List<ProjectASI> projectASIs) {
	this.projectASIs = projectASIs;
    }

}
