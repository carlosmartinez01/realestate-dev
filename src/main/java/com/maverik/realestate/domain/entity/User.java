package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@Entity
@Table(name = "users")
@SonarClassExclusion
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false, updatable = false, insertable = true, length = 128)
    private String username;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "PASSWORD", nullable = false, length = 256)
    private String password;

    @Column(name = "EMAIL", nullable = false, length = 256)
    private String email;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "ACTIVE", nullable = false)
    private Byte active;

    @OneToMany(mappedBy = "usernameId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserRoles> userRoles = new HashSet<UserRoles>();

    @Column(name = "CELLPHONE", nullable = true)
    private String cellPhone;

    @Column(name = "OFFICEPHONE", nullable = true)
    private String officePhone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = true)
    @JoinColumn(name = "COMPANYID", nullable = true)
    private Company company;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyNotes> propertyNotes = new HashSet<PropertyNotes>();

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProjectNotes> projectNotes = new HashSet<ProjectNotes>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "project_user_xref", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "projectId") })
    private Set<Project> projects = new HashSet<Project>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "property_user_xref", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "propertyId") })
    private Set<Property> properties = new HashSet<Property>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCellPhone() {
	return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
	this.cellPhone = cellPhone;
    }

    public String getOfficePhone() {
	return officePhone;
    }

    public void setOfficePhone(String officePhone) {
	this.officePhone = officePhone;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username.trim();
    }

    public Date getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Byte getActive() {
	return active;
    }

    public void setActive(Byte active) {
	this.active = active;
    }

    public Set<UserRoles> getUserRoles() {
	return userRoles;
    }

    public void setUserRoles(Set<UserRoles> userRoles) {
	this.userRoles = userRoles;
    }

    public Company getCompany() {
	return company;
    }

    public void setCompany(Company company) {
	this.company = company;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.username);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final User other = (User) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.username, other.username);
    }

    public Set<Project> getProjects() {
	return projects;
    }

    public void setProjects(Set<Project> projects) {
	this.projects = projects;
    }

    public Set<Property> getProperties() {
	return properties;
    }

    public void setProperties(Set<Property> properties) {
	this.properties = properties;
    }

    public Set<PropertyNotes> getPropertyNotes() {
	return propertyNotes;
    }

    public void setPropertyNotes(Set<PropertyNotes> propertyNotes) {
	this.propertyNotes = propertyNotes;
    }

    public Set<ProjectNotes> getProjectNotes() {
	return projectNotes;
    }

    public void setProjectNotes(Set<ProjectNotes> projectNotes) {
	this.projectNotes = projectNotes;
    }
}
