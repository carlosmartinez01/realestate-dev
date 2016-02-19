/**
 * 
 */
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "property_permitting")
@SecondaryTables({
	@SecondaryTable(name = "property_land_task_commitment", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyPermittingId") }),
	@SecondaryTable(name = "property_land_task_survey", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyPermittingId") }),
	@SecondaryTable(name = "property_land_task_geotechnical", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyPermittingId") }),
	@SecondaryTable(name = "property_land_task_access_permit", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyPermittingId") }),
	@SecondaryTable(name = "property_land_task_engineering", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyPermittingId") }),
	@SecondaryTable(name = "property_land_task_architect", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyPermittingId") }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class PropertyPermitting implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 3080184018636075608L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propertyPermittingId", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "propertyId", nullable = true)
    private Property propertyId;

    @Column(name = "approvalToMoveVP", nullable = false)
    private String approvalToMoveVP;

    @Column(name = "turnOverPCDate", nullable = false)
    private Date turnOverPCDate;

    @Column(name = "civilPlansDate", nullable = false)
    private Date civilPlansDate;

    @Column(name = "buildingPlansDate", nullable = false)
    private Date buildingPlansDate;

    @Column(name = "permittingAssignedTo", nullable = false)
    private String permittingAssignedTo;

    @Column(name = "titleCommitmentReviewed", table = "property_land_task_commitment")
    private String titleCommitmentReviewed;

    @Column(name = "titleCommitmentDate", table = "property_land_task_commitment")
    private Date titleCommitmentDate;

    @Column(name = "commitmentAssignTaskTo", table = "property_land_task_commitment")
    private String commitmentAssignTaskTo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commitmentFileName_id", table = "property_land_task_commitment")
    private Filename commitmentFileName;

    @Column(name = "titleExceptionLetterPrepared", table = "property_land_task_commitment")
    private String titleExceptionLetterPrepared;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadLetterFileName_id", table = "property_land_task_commitment")
    private Filename uploadLetterFileName;

    @Column(name = "exceptionAssignTaskTo", table = "property_land_task_commitment")
    private String exceptionAssignTaskTo;

    @Column(name = "surveyOrdered", table = "property_land_task_survey")
    private String surveyOrdered;

    @Column(name = "surveyDateOrdered", table = "property_land_task_survey")
    private Date surveyDateOrdered;

    @Column(name = "surveyContact", table = "property_land_task_survey")
    private String surveyContact;

    @Column(name = "surveyReceived", table = "property_land_task_survey")
    private String surveyReceived;

    @Column(name = "surveyDateReceived", table = "property_land_task_survey")
    private Date surveyDateReceived;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "surveyFileName_id", table = "property_land_task_survey")
    private Filename surveyFileName;

    @Column(name = "preSitePlanReceived", table = "property_land_task_survey")
    private String preSitePlanReceived;

    @Column(name = "preSitePlanDateReceived", table = "property_land_task_survey")
    private Date preSitePlanDateReceived;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "preSitePlanReceivedFileName_id", table = "property_land_task_survey")
    private Filename preSitePlanReceivedFileName;

    @Column(name = "floodPlainDetermination", table = "property_land_task_survey")
    private String floodPlanDetermination;

    @Column(name = "floodPlainDateDetermined", table = "property_land_task_survey")
    private Date floodPlanDateDetermined;

    @Column(name = "isFloodPlain", table = "property_land_task_survey")
    private String propertyFloodPlain;

    @Column(name = "floodPlainSource", table = "property_land_task_survey")
    private String floodSource;

    @Column(name = "geotechnicalReportOrdered", table = "property_land_task_geotechnical")
    private String geotechnicalReportOrdered;

    @Column(name = "geotechnicalDateReportOrdered", table = "property_land_task_geotechnical")
    private Date geotechnicalDateReportOrdered;

    @Column(name = "geotechnicalEngineer", table = "property_land_task_geotechnical")
    private String geotechnicalEngineer;

    @Column(name = "geotechnicalReportReceived", table = "property_land_task_geotechnical")
    private String geotechnicalReportReceived;

    @Column(name = "geotechnicalReportDateReceived", table = "property_land_task_geotechnical")
    private Date geotechnicalReportDateReceived;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "geotechnicalFileName_id", table = "property_land_task_geotechnical")
    private Filename geotechnicalFileName;

    @Column(name = "departmentTransportation", table = "property_land_task_access_permit")
    private String departmentTransportation;

    @Column(name = "departmentTransportationDateEngaged", table = "property_land_task_access_permit")
    private Date departmentTransportationDateEngaged;

    @Column(name = "trafficStudy", table = "property_land_task_access_permit")
    private String trafficStudy;

    @Column(name = "trafficStudyDateEngaged", table = "property_land_task_access_permit")
    private Date trafficStudyDateEngaged;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trafficStudyFileName_id", table = "property_land_task_access_permit")
    private Filename trafficStudyFileName;

    @Column(name = "civilEngineerAssigned", table = "property_land_task_engineering")
    private String civilEngineerAssigned;

    @Column(name = "civilEngineerDateEngaged", table = "property_land_task_engineering")
    private Date civilEngineerDateEngaged;

    @Column(name = "civilEngineerContact", table = "property_land_task_engineering")
    private String civilEngineerContact;

    @Column(name = "civilEngineerApprovedToBegin", table = "property_land_task_engineering")
    private String civilEngineerApprovedToBegin;

    @Column(name = "civilEngineerMessage", table = "property_land_task_engineering")
    private String civilEngineerMessage;

    @Column(name = "civilEngineerDrawingsReceived", table = "property_land_task_engineering")
    private String civilEngineerDrawingsReceived;

    @Column(name = "civilEngineerDrawingsDateReceived", table = "property_land_task_engineering")
    private Date civilEngineerDrawingsDateReceived;

    @Column(name = "gradingAndDrainagePlan", table = "property_land_task_engineering")
    private String gradingAndDrainagePlan;

    @Column(name = "gradingAndDrainagePlanDateApproved", table = "property_land_task_engineering")
    private Date gradingAndDrainagePlanDateApproved;

    @Column(name = "gradingAndDrainageAssignTaskTo", table = "property_land_task_engineering")
    private String gradingAndDrainageAssignTaskTo;

    @Column(name = "architectAssigned", table = "property_land_task_architect")
    private String architectAssigned;

    @Column(name = "architectDateEngaged", table = "property_land_task_architect")
    private Date architectDateEngaged;

    @Column(name = "architectName", table = "property_land_task_architect")
    private String architectName;

    @Column(name = "architectApprovedToBegin", table = "property_land_task_architect")
    private String architectApprovedToBegin;

    @Column(name = "architectApprovedMessage", table = "property_land_task_architect")
    private String architectApprovedMessage;

    @OneToMany(mappedBy = "propertyPermittingId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyMeeting> propertyMeeting = new HashSet<PropertyMeeting>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Property getPropertyId() {
	return propertyId;
    }

    public void setPropertyId(Property propertyId) {
	this.propertyId = propertyId;
    }

    public String getApprovalToMoveVP() {
	return approvalToMoveVP;
    }

    public void setApprovalToMoveVP(String approvalToMoveVP) {
	this.approvalToMoveVP = approvalToMoveVP;
    }

    public Date getTurnOverPCDate() {
	return turnOverPCDate;
    }

    public void setTurnOverPCDate(Date turnOverPCDate) {
	this.turnOverPCDate = turnOverPCDate;
    }

    public Date getCivilPlansDate() {
	return civilPlansDate;
    }

    public void setCivilPlansDate(Date civilPlansDate) {
	this.civilPlansDate = civilPlansDate;
    }

    public Date getBuildingPlansDate() {
	return buildingPlansDate;
    }

    public void setBuildingPlansDate(Date buildingPlansDate) {
	this.buildingPlansDate = buildingPlansDate;
    }

    public String getPermittingAssignedTo() {
	return permittingAssignedTo;
    }

    public void setPermittingAssignedTo(String permittingAssignedTo) {
	this.permittingAssignedTo = permittingAssignedTo;
    }

    @Override
    public String toString() {
	return "PropertyPermitting [id=" + id + ", propertyId=" + propertyId
		+ "]";
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
	final PropertyPermitting other = (PropertyPermitting) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    public String getTitleCommitmentReviewed() {
	return titleCommitmentReviewed;
    }

    public void setTitleCommitmentReviewed(String tittleCommitmentReviewed) {
	this.titleCommitmentReviewed = tittleCommitmentReviewed;
    }

    public Date getTitleCommitmentDate() {
	return titleCommitmentDate;
    }

    public void setTitleCommitmentDate(Date titleCommitmentDate) {
	this.titleCommitmentDate = titleCommitmentDate;
    }

    public String getCommitmentAssignTaskTo() {
	return commitmentAssignTaskTo;
    }

    public void setCommitmentAssignTaskTo(String commitmentAssignTaskTo) {
	this.commitmentAssignTaskTo = commitmentAssignTaskTo;
    }

    public Filename getCommitmentFileName() {
	return commitmentFileName;
    }

    public void setCommitmentFileName(Filename commitmentFileName) {
	this.commitmentFileName = commitmentFileName;
    }

    public String getTitleExceptionLetterPrepared() {
	return titleExceptionLetterPrepared;
    }

    public void setTitleExceptionLetterPrepared(
	    String titleExceptionLetterPrepared) {
	this.titleExceptionLetterPrepared = titleExceptionLetterPrepared;
    }

    public Filename getUploadLetterFileName() {
	return uploadLetterFileName;
    }

    public void setUploadLetterFileName(Filename uploadLetterFileName) {
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

    public Date getSurveyDateOrdered() {
	return surveyDateOrdered;
    }

    public void setSurveyDateOrdered(Date surveyDateOrdered) {
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

    public Date getSurveyDateReceived() {
	return surveyDateReceived;
    }

    public void setSurveyDateReceived(Date surveyDateReceived) {
	this.surveyDateReceived = surveyDateReceived;
    }

    public Filename getSurveyFileName() {
	return surveyFileName;
    }

    public void setSurveyFileName(Filename surveyFileName) {
	this.surveyFileName = surveyFileName;
    }

    public String getPreSitePlanReceived() {
	return preSitePlanReceived;
    }

    public void setPreSitePlanReceived(String preSitePlanReceived) {
	this.preSitePlanReceived = preSitePlanReceived;
    }

    public Date getPreSitePlanDateReceived() {
	return preSitePlanDateReceived;
    }

    public void setPreSitePlanDateReceived(Date preSitePlanDateReceived) {
	this.preSitePlanDateReceived = preSitePlanDateReceived;
    }

    public Filename getPreSitePlanReceivedFileName() {
	return preSitePlanReceivedFileName;
    }

    public void setPreSitePlanReceivedFileName(
	    Filename preSitePlanReceivedFileName) {
	this.preSitePlanReceivedFileName = preSitePlanReceivedFileName;
    }

    public String getFloodPlanDetermination() {
	return floodPlanDetermination;
    }

    public void setFloodPlanDetermination(String floodPlanDetermination) {
	this.floodPlanDetermination = floodPlanDetermination;
    }

    public Date getFloodPlanDateDetermined() {
	return floodPlanDateDetermined;
    }

    public void setFloodPlanDateDetermined(Date floodPlanDateDetermined) {
	this.floodPlanDateDetermined = floodPlanDateDetermined;
    }

    public String getGeotechnicalReportOrdered() {
	return geotechnicalReportOrdered;
    }

    public void setGeotechnicalReportOrdered(String geotechnicalReportOrdered) {
	this.geotechnicalReportOrdered = geotechnicalReportOrdered;
    }

    public Date getGeotechnicalDateReportOrdered() {
	return geotechnicalDateReportOrdered;
    }

    public void setGeotechnicalDateReportOrdered(
	    Date geotechnicalDateReportOrdered) {
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

    public Date getGeotechnicalReportDateReceived() {
	return geotechnicalReportDateReceived;
    }

    public void setGeotechnicalReportDateReceived(
	    Date geotechnicalReportDateReceived) {
	this.geotechnicalReportDateReceived = geotechnicalReportDateReceived;
    }

    public Filename getGeotechnicalFileName() {
	return geotechnicalFileName;
    }

    public void setGeotechnicalFileName(Filename geotechnicalFileName) {
	this.geotechnicalFileName = geotechnicalFileName;
    }

    public String getDepartmentTransportation() {
	return departmentTransportation;
    }

    public void setDepartmentTransportation(String departmentTransportation) {
	this.departmentTransportation = departmentTransportation;
    }

    public Date getDepartmentTransportationDateEngaged() {
	return departmentTransportationDateEngaged;
    }

    public void setDepartmentTransportationDateEngaged(
	    Date departmentTransportationDateEngaged) {
	this.departmentTransportationDateEngaged = departmentTransportationDateEngaged;
    }

    public String getTrafficStudy() {
	return trafficStudy;
    }

    public void setTrafficStudy(String trafficStudy) {
	this.trafficStudy = trafficStudy;
    }

    public Date getTrafficStudyDateEngaged() {
	return trafficStudyDateEngaged;
    }

    public void setTrafficStudyDateEngaged(Date trafficStudyDateEngaged) {
	this.trafficStudyDateEngaged = trafficStudyDateEngaged;
    }

    public Filename getTrafficStudyFileName() {
	return trafficStudyFileName;
    }

    public void setTrafficStudyFileName(Filename trafficStudyFileName) {
	this.trafficStudyFileName = trafficStudyFileName;
    }

    public String getCivilEngineerAssigned() {
	return civilEngineerAssigned;
    }

    public void setCivilEngineerAssigned(String civilEngineerAssigned) {
	this.civilEngineerAssigned = civilEngineerAssigned;
    }

    public Date getCivilEngineerDateEngaged() {
	return civilEngineerDateEngaged;
    }

    public void setCivilEngineerDateEngaged(Date civilEngineerDateEngaged) {
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

    public Date getCivilEngineerDrawingsDateReceived() {
	return civilEngineerDrawingsDateReceived;
    }

    public void setCivilEngineerDrawingsDateReceived(
	    Date civilEngineerDrawingsDateReceived) {
	this.civilEngineerDrawingsDateReceived = civilEngineerDrawingsDateReceived;
    }

    public String getGradingAndDrainagePlan() {
	return gradingAndDrainagePlan;
    }

    public void setGradingAndDrainagePlan(String gradingAndDrainagePlan) {
	this.gradingAndDrainagePlan = gradingAndDrainagePlan;
    }

    public Date getGradingAndDrainagePlanDateApproved() {
	return gradingAndDrainagePlanDateApproved;
    }

    public void setGradingAndDrainagePlanDateApproved(
	    Date gradingAndDrainagePlanDateApproved) {
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

    public Date getArchitectDateEngaged() {
	return architectDateEngaged;
    }

    public void setArchitectDateEngaged(Date architectDateEngaged) {
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

    public Set<PropertyMeeting> getPropertyMeeting() {
	return propertyMeeting;
    }

    public void setPropertyMeeting(Set<PropertyMeeting> propertyMeeting) {
	this.propertyMeeting = propertyMeeting;
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
