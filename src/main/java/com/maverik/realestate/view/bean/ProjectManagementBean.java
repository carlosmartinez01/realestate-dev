package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

public class ProjectManagementBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -6340873787919614276L;

  private Long id;

  private Long project;

  private String projectManager;

  private String budgetSent;

  private String budgetDateSent;

  private String approvedConstructionBudget;

  private String constructionBudgetDateApproved;

  private FileBean approvedBudgetFile;

  private String generalContractor;

  private String insuranceContractorCompanyReceived;

  private String contractorProjectManager;

  private String contractorSupervisor;

  private String testingCompany;

  private String insuranceTestingCompanyReceived;

  private String petroleumInstaller;

  private String insurancePetroleumReceived;

  private String startConstructionDate;

  private String endConstructionDate;

  private String noticeDateEightWeeks;

  private String noticeDateFourWeeks;

  private String storeClosingFromDate;

  private String storeClosingToDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProject() {
    return project;
  }

  public void setProject(Long project) {
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

  public String getBudgetDateSent() {
    return budgetDateSent;
  }

  public void setBudgetDateSent(String budgetDateSent) {
    this.budgetDateSent = budgetDateSent;
  }

  public String getApprovedConstructionBudget() {
    return approvedConstructionBudget;
  }

  public void setApprovedConstructionBudget(String approvedConstructionBudget) {
    this.approvedConstructionBudget = approvedConstructionBudget;
  }

  public String getConstructionBudgetDateApproved() {
    return constructionBudgetDateApproved;
  }

  public void setConstructionBudgetDateApproved(String constructionBudgetDateApproved) {
    this.constructionBudgetDateApproved = constructionBudgetDateApproved;
  }

  public FileBean getApprovedBudgetFile() {
    return approvedBudgetFile;
  }

  public void setApprovedBudgetFile(FileBean approvedBudgetFile) {
    this.approvedBudgetFile = approvedBudgetFile;
  }

  public String getGeneralContractor() {
    return generalContractor;
  }

  public void setGeneralContractor(String generalContractor) {
    this.generalContractor = generalContractor;
  }

  public String getInsuranceContractorCompanyReceived() {
    return insuranceContractorCompanyReceived;
  }

  public void setInsuranceContractorCompanyReceived(String insuranceContractorCompanyReceived) {
    this.insuranceContractorCompanyReceived = insuranceContractorCompanyReceived;
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

  public void setInsuranceTestingCompanyReceived(String insuranceTestingCompanyReceived) {
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

  public String getStartConstructionDate() {
    return startConstructionDate;
  }

  public void setStartConstructionDate(String startConstructionDate) {
    this.startConstructionDate = startConstructionDate;
  }

  public String getEndConstructionDate() {
    return endConstructionDate;
  }

  public void setEndConstructionDate(String endConstructionDate) {
    this.endConstructionDate = endConstructionDate;
  }

  public String getNoticeDateEightWeeks() {
    return noticeDateEightWeeks;
  }

  public void setNoticeDateEightWeeks(String noticeDateEightWeeks) {
    this.noticeDateEightWeeks = noticeDateEightWeeks;
  }

  public String getNoticeDateFourWeeks() {
    return noticeDateFourWeeks;
  }

  public void setNoticeDateFourWeeks(String noticeDateFourWeeks) {
    this.noticeDateFourWeeks = noticeDateFourWeeks;
  }

  public String getStoreClosingFromDate() {
    return storeClosingFromDate;
  }

  public void setStoreClosingFromDate(String storeClosingFromDate) {
    this.storeClosingFromDate = storeClosingFromDate;
  }

  public String getStoreClosingToDate() {
    return storeClosingToDate;
  }

  public void setStoreClosingToDate(String storeClosingToDate) {
    this.storeClosingToDate = storeClosingToDate;
  }

  @Override
  public String toString() {
    return "ProjectManagementBean [id=" + id + ", project=" + project + ", projectManager="
        + projectManager + ", budgetSent=" + budgetSent + ", budgetDateSent=" + budgetDateSent
        + ", approvedConstructionBudget=" + approvedConstructionBudget
        + ", constructionBudgetDateApproved=" + constructionBudgetDateApproved
        + ", approvedBudgetFile=" + approvedBudgetFile + ", generalContractor=" + generalContractor
        + ", insuranceContractorCompanyReceived=" + insuranceContractorCompanyReceived
        + ", contractorProjectManager=" + contractorProjectManager + ", contractorSupervisor="
        + contractorSupervisor + ", testingCompany=" + testingCompany
        + ", insuranceTestingCompanyReceived=" + insuranceTestingCompanyReceived
        + ", petroleumInstaller=" + petroleumInstaller + ", insurancePetroleumReceived="
        + insurancePetroleumReceived + ", startConstructionDate=" + startConstructionDate
        + ", endConstructionDate=" + endConstructionDate + ", noticeDateEightWeeks="
        + noticeDateEightWeeks + ", noticeDateFourWeeks=" + noticeDateFourWeeks
        + ", storeClosingFromDate=" + storeClosingFromDate + ", storeClosingToDate="
        + storeClosingToDate + "]";
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
    final ProjectManagementBean other = (ProjectManagementBean) obj;
    return Objects.equals(this.id, other.id) && Objects.equals(this.project, other.project);
  }
}
