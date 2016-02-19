/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PropertyPermittingBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -5245877780621764594L;

    private Long id;

    private Long propertyId;

    private String approvalToMoveVP;

    private String turnOverPCDate;

    private String civilPlansDate;

    private String buildingPlansDate;

    private String permittingAssignedTo;

    private String titleCommitmentReviewed;

    private String titleCommitmentDate;

    private String commitmentAssignTaskTo;

    private FileBean commitmentFileName;

    private String titleExceptionLetterPrepared;

    private FileBean uploadLetterFileName;

    private String exceptionAssignTaskTo;

    private String surveyOrdered;

    private String surveyDateOrdered;

    private String surveyContact;

    private String surveyReceived;

    private String surveyDateReceived;

    private FileBean surveyFileName;

    private String preSitePlanReceived;

    private String preSitePlanDateReceived;

    private FileBean preSitePlanReceivedFileName;

    private String floodPlanDetermination;

    private String floodPlanDateDetermined;

    private String propertyFloodPlain;

    private String floodSource;

    private String geotechnicalReportOrdered;

    private String geotechnicalDateReportOrdered;

    private String geotechnicalEngineer;

    private String geotechnicalReportReceived;

    private String geotechnicalReportDateReceived;

    private FileBean geotechnicalFileName;

    private String departmentTransportation;

    private String departmentTransportationDateEngaged;

    private String trafficStudy;

    private String trafficStudyDateEngaged;

    private FileBean trafficStudyFileName;

    private String civilEngineerAssigned;

    private String civilEngineerDateEngaged;

    private String civilEngineerContact;

    private String civilEngineerApprovedToBegin;

    private String civilEngineerMessage;

    private String civilEngineerDrawingsReceived;

    private String civilEngineerDrawingsDateReceived;

    private String gradingAndDrainagePlan;

    private String gradingAndDrainagePlanDateApproved;

    private String gradingAndDrainageAssignTaskTo;

    private String architectAssigned;

    private String architectDateEngaged;

    private String architectName;

    private String architectApprovedToBegin;

    private String architectApprovedMessage;

    @Override
    public String toString() {
	return "PropertyPermittingBean [id=" + id + ", propertyId="
		+ propertyId + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.propertyId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PropertyPermittingBean other = (PropertyPermittingBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getPropertyId() {
	return propertyId;
    }

    public void setPropertyId(Long propertyId) {
	this.propertyId = propertyId;
    }

    public String getApprovalToMoveVP() {
	return approvalToMoveVP;
    }

    public void setApprovalToMoveVP(String approvalToMoveVP) {
	this.approvalToMoveVP = approvalToMoveVP;
    }

    public String getTurnOverPCDate() {
	return turnOverPCDate;
    }

    public void setTurnOverPCDate(String turnOverPCDate) {
	this.turnOverPCDate = turnOverPCDate;
    }

    public String getCivilPlansDate() {
	return civilPlansDate;
    }

    public void setCivilPlansDate(String civilPlansDate) {
	this.civilPlansDate = civilPlansDate;
    }

    public String getBuildingPlansDate() {
	return buildingPlansDate;
    }

    public void setBuildingPlansDate(String buildingPlansDate) {
	this.buildingPlansDate = buildingPlansDate;
    }

    public String getPermittingAssignedTo() {
	return permittingAssignedTo;
    }

    public void setPermittingAssignedTo(String permittingAssignedTo) {
	this.permittingAssignedTo = permittingAssignedTo;
    }

    public String getTitleCommitmentReviewed() {
	return titleCommitmentReviewed;
    }

    public void setTitleCommitmentReviewed(String tittleCommitmentReviewed) {
	this.titleCommitmentReviewed = tittleCommitmentReviewed;
    }

    public String getTitleCommitmentDate() {
	return titleCommitmentDate;
    }

    public void setTitleCommitmentDate(String titleCommitmentDate) {
	this.titleCommitmentDate = titleCommitmentDate;
    }

    public String getCommitmentAssignTaskTo() {
	return commitmentAssignTaskTo;
    }

    public void setCommitmentAssignTaskTo(String commitmentAssignTaskTo) {
	this.commitmentAssignTaskTo = commitmentAssignTaskTo;
    }

    public FileBean getCommitmentFileName() {
	return commitmentFileName;
    }

    public void setCommitmentFileName(FileBean commitmentFileName) {
	this.commitmentFileName = commitmentFileName;
    }

    public String getTitleExceptionLetterPrepared() {
	return titleExceptionLetterPrepared;
    }

    public void setTitleExceptionLetterPrepared(
	    String titleExceptionLetterPrepared) {
	this.titleExceptionLetterPrepared = titleExceptionLetterPrepared;
    }

    public FileBean getUploadLetterFileName() {
	return uploadLetterFileName;
    }

    public void setUploadLetterFileName(FileBean uploadLetterFileName) {
	this.uploadLetterFileName = uploadLetterFileName;
    }

    public String getExceptionAssignTaskTo() {
	return exceptionAssignTaskTo;
    }

    public void setExceptionAssignTaskTo(String exceptionAssignTaskTo) {
	this.exceptionAssignTaskTo = exceptionAssignTaskTo;
    }

    public String getSurveyOrdered() {
	return surveyOrdered;
    }

    public void setSurveyOrdered(String surveyOrdered) {
	this.surveyOrdered = surveyOrdered;
    }

    public String getSurveyDateOrdered() {
	return surveyDateOrdered;
    }

    public void setSurveyDateOrdered(String surveyDateOrdered) {
	this.surveyDateOrdered = surveyDateOrdered;
    }

    public String getSurveyContact() {
	return surveyContact;
    }

    public void setSurveyContact(String surveyContact) {
	this.surveyContact = surveyContact;
    }

    public String getSurveyReceived() {
	return surveyReceived;
    }

    public void setSurveyReceived(String surveyReceived) {
	this.surveyReceived = surveyReceived;
    }

    public String getSurveyDateReceived() {
	return surveyDateReceived;
    }

    public void setSurveyDateReceived(String surveyDateReceived) {
	this.surveyDateReceived = surveyDateReceived;
    }

    public FileBean getSurveyFileName() {
	return surveyFileName;
    }

    public void setSurveyFileName(FileBean surveyFileName) {
	this.surveyFileName = surveyFileName;
    }

    public String getPreSitePlanReceived() {
	return preSitePlanReceived;
    }

    public void setPreSitePlanReceived(String preSitePlanReceived) {
	this.preSitePlanReceived = preSitePlanReceived;
    }

    public String getPreSitePlanDateReceived() {
	return preSitePlanDateReceived;
    }

    public void setPreSitePlanDateReceived(String preSitePlanDateReceived) {
	this.preSitePlanDateReceived = preSitePlanDateReceived;
    }

    public FileBean getPreSitePlanReceivedFileName() {
	return preSitePlanReceivedFileName;
    }

    public void setPreSitePlanReceivedFileName(
	    FileBean preSitePlanReceivedFileName) {
	this.preSitePlanReceivedFileName = preSitePlanReceivedFileName;
    }

    public String getFloodPlanDetermination() {
	return floodPlanDetermination;
    }

    public void setFloodPlanDetermination(String floodPlanDetermination) {
	this.floodPlanDetermination = floodPlanDetermination;
    }

    public String getFloodPlanDateDetermined() {
	return floodPlanDateDetermined;
    }

    public void setFloodPlanDateDetermined(String floodPlanDateDetermined) {
	this.floodPlanDateDetermined = floodPlanDateDetermined;
    }

    public String getGeotechnicalReportOrdered() {
	return geotechnicalReportOrdered;
    }

    public void setGeotechnicalReportOrdered(String geotechnicalReportOrdered) {
	this.geotechnicalReportOrdered = geotechnicalReportOrdered;
    }

    public String getGeotechnicalDateReportOrdered() {
	return geotechnicalDateReportOrdered;
    }

    public void setGeotechnicalDateReportOrdered(
	    String geotechnicalDateReportOrdered) {
	this.geotechnicalDateReportOrdered = geotechnicalDateReportOrdered;
    }

    public String getGeotechnicalEngineer() {
	return geotechnicalEngineer;
    }

    public void setGeotechnicalEngineer(String geotechnicalEngineer) {
	this.geotechnicalEngineer = geotechnicalEngineer;
    }

    public String getGeotechnicalReportReceived() {
	return geotechnicalReportReceived;
    }

    public void setGeotechnicalReportReceived(String geotechnicalReportReceived) {
	this.geotechnicalReportReceived = geotechnicalReportReceived;
    }

    public String getGeotechnicalReportDateReceived() {
	return geotechnicalReportDateReceived;
    }

    public void setGeotechnicalReportDateReceived(
	    String geotechnicalReportDateReceived) {
	this.geotechnicalReportDateReceived = geotechnicalReportDateReceived;
    }

    public FileBean getGeotechnicalFileName() {
	return geotechnicalFileName;
    }

    public void setGeotechnicalFileName(FileBean geotechnicalFileName) {
	this.geotechnicalFileName = geotechnicalFileName;
    }

    public String getDepartmentTransportation() {
	return departmentTransportation;
    }

    public void setDepartmentTransportation(String departmentTransportation) {
	this.departmentTransportation = departmentTransportation;
    }

    public String getDepartmentTransportationDateEngaged() {
	return departmentTransportationDateEngaged;
    }

    public void setDepartmentTransportationDateEngaged(
	    String departmentTransportationDateEngaged) {
	this.departmentTransportationDateEngaged = departmentTransportationDateEngaged;
    }

    public String getTrafficStudy() {
	return trafficStudy;
    }

    public void setTrafficStudy(String trafficStudy) {
	this.trafficStudy = trafficStudy;
    }

    public String getTrafficStudyDateEngaged() {
	return trafficStudyDateEngaged;
    }

    public void setTrafficStudyDateEngaged(String trafficStudyDateEngaged) {
	this.trafficStudyDateEngaged = trafficStudyDateEngaged;
    }

    public FileBean getTrafficStudyFileName() {
	return trafficStudyFileName;
    }

    public void setTrafficStudyFileName(FileBean trafficStudyFileName) {
	this.trafficStudyFileName = trafficStudyFileName;
    }

    public String getCivilEngineerAssigned() {
	return civilEngineerAssigned;
    }

    public void setCivilEngineerAssigned(String civilEngineerAssigned) {
	this.civilEngineerAssigned = civilEngineerAssigned;
    }

    public String getCivilEngineerDateEngaged() {
	return civilEngineerDateEngaged;
    }

    public void setCivilEngineerDateEngaged(String civilEngineerDateEngaged) {
	this.civilEngineerDateEngaged = civilEngineerDateEngaged;
    }

    public String getCivilEngineerContact() {
	return civilEngineerContact;
    }

    public void setCivilEngineerContact(String civilEngineerContact) {
	this.civilEngineerContact = civilEngineerContact;
    }

    public String getCivilEngineerApprovedToBegin() {
	return civilEngineerApprovedToBegin;
    }

    public void setCivilEngineerApprovedToBegin(
	    String civilEngineerApprovedToBegin) {
	this.civilEngineerApprovedToBegin = civilEngineerApprovedToBegin;
    }

    public String getCivilEngineerMessage() {
	return civilEngineerMessage;
    }

    public void setCivilEngineerMessage(String civilEngineerMessage) {
	this.civilEngineerMessage = civilEngineerMessage;
    }

    public String getCivilEngineerDrawingsReceived() {
	return civilEngineerDrawingsReceived;
    }

    public void setCivilEngineerDrawingsReceived(
	    String civilEngineerDrawingsReceived) {
	this.civilEngineerDrawingsReceived = civilEngineerDrawingsReceived;
    }

    public String getCivilEngineerDrawingsDateReceived() {
	return civilEngineerDrawingsDateReceived;
    }

    public void setCivilEngineerDrawingsDateReceived(
	    String civilEngineerDrawingsDateReceived) {
	this.civilEngineerDrawingsDateReceived = civilEngineerDrawingsDateReceived;
    }

    public String getGradingAndDrainagePlan() {
	return gradingAndDrainagePlan;
    }

    public void setGradingAndDrainagePlan(String gradingAndDrainagePlan) {
	this.gradingAndDrainagePlan = gradingAndDrainagePlan;
    }

    public String getGradingAndDrainagePlanDateApproved() {
	return gradingAndDrainagePlanDateApproved;
    }

    public void setGradingAndDrainagePlanDateApproved(
	    String gradingAndDrainagePlanDateApproved) {
	this.gradingAndDrainagePlanDateApproved = gradingAndDrainagePlanDateApproved;
    }

    public String getGradingAndDrainageAssignTaskTo() {
	return gradingAndDrainageAssignTaskTo;
    }

    public void setGradingAndDrainageAssignTaskTo(
	    String gradingAndDrainageAssignTaskTo) {
	this.gradingAndDrainageAssignTaskTo = gradingAndDrainageAssignTaskTo;
    }

    public String getArchitectAssigned() {
	return architectAssigned;
    }

    public void setArchitectAssigned(String architectAssigned) {
	this.architectAssigned = architectAssigned;
    }

    public String getArchitectDateEngaged() {
	return architectDateEngaged;
    }

    public void setArchitectDateEngaged(String architectDateEngaged) {
	this.architectDateEngaged = architectDateEngaged;
    }

    public String getArchitectName() {
	return architectName;
    }

    public void setArchitectName(String architectName) {
	this.architectName = architectName;
    }

    public String getArchitectApprovedToBegin() {
	return architectApprovedToBegin;
    }

    public void setArchitectApprovedToBegin(String architectApprovedToBegin) {
	this.architectApprovedToBegin = architectApprovedToBegin;
    }

    public String getArchitectApprovedMessage() {
	return architectApprovedMessage;
    }

    public void setArchitectApprovedMessage(String architectApprovedMessage) {
	this.architectApprovedMessage = architectApprovedMessage;
    }

    public String getPropertyFloodPlain() {
	return propertyFloodPlain;
    }

    public void setPropertyFloodPlain(String propertyFloodPlain) {
	this.propertyFloodPlain = propertyFloodPlain;
    }

    public String getFloodSource() {
	return floodSource;
    }

    public void setFloodSource(String floodSource) {
	this.floodSource = floodSource;
    }
}
