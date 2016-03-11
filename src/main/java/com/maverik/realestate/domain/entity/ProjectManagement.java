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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_management")
public class ProjectManagement implements Serializable {

    /**
   * 
   */
    private static final long serialVersionUID = 7399364871972412813L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @Column(name = "projectManager")
    private String projectManager;

    @Column(name = "isBudgetSent")
    private String budgetSent;

    @Column(name = "budgetDateSent")
    private Date budgetDateSent;

    @Column(name = "isApprovedConstructionBudget")
    private String approvedConstructionBudget;

    @Column(name = "constructionBudgetDateApproved")
    private Date constructionBudgetDateApproved;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "approvedBudgetFileId")
    private Filename approvedBudgetFile;

    @Column(name = "generalContractor")
    private String generalContractor;

    @Column(name = "isInsuranceReceived")
    private String insuranceContractorCompanyReceived;

    @Column(name = "contractorPM")
    private String contractorProjectManager;

    @Column(name = "contractorSupervisor")
    private String contractorSupervisor;

    @Column(name = "testingCompany")
    private String testingCompany;

    @Column(name = "isInsuranceTestingReceived")
    private String insuranceTestingCompanyReceived;

    @Column(name = "petroleumInstaller")
    private String petroleumInstaller;

    @Column(name = "isInsurencePetroleumReceived")
    private String insurancePetroleumReceived;

    @Column(name = "startConstructionDate")
    private Date startConstructionDate;

    @Column(name = "endConstructionDate")
    private Date endConstructionDate;

    @Column(name = "noticeDateEightWeeks")
    private Date noticeDateEightWeeks;

    @Column(name = "noticeDateFourWeeks")
    private Date noticeDateFourWeeks;

    @Column(name = "storeClosingFromDate")
    private Date storeClosingFromDate;

    @Column(name = "storeClosingToDate")
    private Date storeClosingToDate;

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

    public String getProjectManager() {
	return projectManager;
    }

    public void setProjectManager(String projectManager) {
	this.projectManager = projectManager;
    }

    public String getBudgetSent() {
	return budgetSent;
    }

    public void setBudgetSent(String budgetSent) {
	this.budgetSent = budgetSent;
    }

    public Date getBudgetDateSent() {
	return budgetDateSent;
    }

    public void setBudgetDateSent(Date budgetDateSent) {
	this.budgetDateSent = budgetDateSent;
    }

    public String getApprovedConstructionBudget() {
	return approvedConstructionBudget;
    }

    public void setApprovedConstructionBudget(String approvedConstructionBudget) {
	this.approvedConstructionBudget = approvedConstructionBudget;
    }

    public Date getConstructionBudgetDateApproved() {
	return constructionBudgetDateApproved;
    }

    public void setConstructionBudgetDateApproved(
	    Date constructionBudgetDateApproved) {
	this.constructionBudgetDateApproved = constructionBudgetDateApproved;
    }

    public Filename getApprovedBudgetFile() {
	return approvedBudgetFile;
    }

    public void setApprovedBudgetFile(Filename approvedBudgetFile) {
	this.approvedBudgetFile = approvedBudgetFile;
    }

    public String getGeneralContractor() {
	return generalContractor;
    }

    public void setGeneralContractor(String generalContractor) {
	this.generalContractor = generalContractor;
    }

    public String getContractorProjectManager() {
	return contractorProjectManager;
    }

    public void setContractorProjectManager(String contractorProjectManager) {
	this.contractorProjectManager = contractorProjectManager;
    }

    public String getContractorSupervisor() {
	return contractorSupervisor;
    }

    public void setContractorSupervisor(String contractorSupervisor) {
	this.contractorSupervisor = contractorSupervisor;
    }

    public String getTestingCompany() {
	return testingCompany;
    }

    public void setTestingCompany(String testingCompany) {
	this.testingCompany = testingCompany;
    }

    public String getInsuranceTestingCompanyReceived() {
	return insuranceTestingCompanyReceived;
    }

    public void setInsuranceTestingCompanyReceived(
	    String insuranceTestingCompanyReceived) {
	this.insuranceTestingCompanyReceived = insuranceTestingCompanyReceived;
    }

    public String getPetroleumInstaller() {
	return petroleumInstaller;
    }

    public void setPetroleumInstaller(String petroleumInstaller) {
	this.petroleumInstaller = petroleumInstaller;
    }

    public String getInsurancePetroleumReceived() {
	return insurancePetroleumReceived;
    }

    public void setInsurancePetroleumReceived(String insurancePetroleumReceived) {
	this.insurancePetroleumReceived = insurancePetroleumReceived;
    }

    public Date getStartConstructionDate() {
	return startConstructionDate;
    }

    public void setStartConstructionDate(Date startConstructionDate) {
	this.startConstructionDate = startConstructionDate;
    }

    public Date getEndConstructionDate() {
	return endConstructionDate;
    }

    public void setEndConstructionDate(Date endConstructionDate) {
	this.endConstructionDate = endConstructionDate;
    }

    public Date getNoticeDateEightWeeks() {
	return noticeDateEightWeeks;
    }

    public void setNoticeDateEightWeeks(Date noticeDateEightWeeks) {
	this.noticeDateEightWeeks = noticeDateEightWeeks;
    }

    public Date getNoticeDateFourWeeks() {
	return noticeDateFourWeeks;
    }

    public void setNoticeDateFourWeeks(Date noticeDateFourWeeks) {
	this.noticeDateFourWeeks = noticeDateFourWeeks;
    }

    public Date getStoreClosingFromDate() {
	return storeClosingFromDate;
    }

    public void setStoreClosingFromDate(Date storeClosingFromDate) {
	this.storeClosingFromDate = storeClosingFromDate;
    }

    public Date getStoreClosingToDate() {
	return storeClosingToDate;
    }

    public void setStoreClosingToDate(Date storeClosingToDate) {
	this.storeClosingToDate = storeClosingToDate;
    }

    @Override
    public String toString() {
	return "ProjectManagement [id=" + id + ", project=" + project
		+ ", projectManager=" + projectManager + "]";
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
	final ProjectManagement other = (ProjectManagement) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project, other.project);
    }

    public String getInsuranceContractorCompanyReceived() {
	return insuranceContractorCompanyReceived;
    }

    public void setInsuranceContractorCompanyReceived(
	    String insuranceContractorCompanyReceived) {
	this.insuranceContractorCompanyReceived = insuranceContractorCompanyReceived;
    }
}
