package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "project_close_out")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class ProjectCloseOut implements Serializable {

    /**
   * 
   */
    private static final long serialVersionUID = -4166126254322612307L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @Column(name = "isRedlines")
    private String redlines;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "redlinesFileId")
    private Filename redlinesFile;

    @Column(name = "isGeneralContractorWarranties")
    private String generalContractorWarranties;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "generalContractorFileId")
    private Filename generalContractorFile;

    @Column(name = "isPunchListComplete")
    private String punchListComplete;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "punchListItemsFileId")
    private Filename punchListItemsFile;

    @Column(name = "isUtilitiesTurnover")
    private String utilitiesTurnover;

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

    public String getRedlines() {
	return redlines;
    }

    public void setRedlines(String redlines) {
	this.redlines = redlines;
    }

    public Filename getRedlinesFile() {
	return redlinesFile;
    }

    public void setRedlinesFile(Filename redlinesFile) {
	this.redlinesFile = redlinesFile;
    }

    public String getGeneralContractorWarranties() {
	return generalContractorWarranties;
    }

    public void setGeneralContractorWarranties(
	    String generalContractorWarranties) {
	this.generalContractorWarranties = generalContractorWarranties;
    }

    public Filename getGeneralContractorFile() {
	return generalContractorFile;
    }

    public void setGeneralContractorFile(Filename generalContractorFile) {
	this.generalContractorFile = generalContractorFile;
    }

    public String getPunchListComplete() {
	return punchListComplete;
    }

    public void setPunchListComplete(String punchListComplete) {
	this.punchListComplete = punchListComplete;
    }

    public Filename getPunchListItemsFile() {
	return punchListItemsFile;
    }

    public void setPunchListItemsFile(Filename punchListItemsFile) {
	this.punchListItemsFile = punchListItemsFile;
    }

    public String getUtilitiesTurnover() {
	return utilitiesTurnover;
    }

    public void setUtilitiesTurnover(String utilitiesTurnover) {
	this.utilitiesTurnover = utilitiesTurnover;
    }

    @Override
    public String toString() {
	return "ProjectCloseOut [id=" + id + ", project=" + project + "]";
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
	final ProjectCloseOut other = (ProjectCloseOut) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project, other.project);
    }
}
