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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
@Entity
@Table(name = "page")
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "PAGENAME", nullable = false, length = 256)
    private String pageName;

    @Column(name = "DESCRIPTION", nullable = false, length = 256)
    private String description;

    @Column(name = "SHORTNAME", nullable = false, length = 100)
    private String shortName;

    @OneToMany(mappedBy = "pageId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyNotes> propertyNotes = new HashSet<PropertyNotes>();

    @OneToMany(mappedBy = "pageId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProjectNotes> projectNotes = new HashSet<ProjectNotes>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getPageName() {
	return pageName;
    }

    public void setPageName(String pageName) {
	this.pageName = pageName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final Page other = (Page) obj;
	return Objects.equals(this.id, other.id);
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

    public String getShortName() {
	return shortName;
    }

    public void setShortName(String shortName) {
	this.shortName = shortName;
    }

}
