/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@Entity
@Table(name = "daily_reports")
@SonarClassExclusion
public class DailyReport implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -26890889698977511L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reportedBy", nullable = false)
    private User reportedBy;

    @Column(name = "scopeWorkDesc")
    private String scopeWorkDesc;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sureTrackFile")
    private Filename sureTrackFile;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "imageFile01")
    private Filename imageFile01;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "imageFile02")
    private Filename imageFile02;

    @Column(name = "isWeatherDay")
    private String isWeatherDay;

    @Column(name = "actualEndDate")
    private Date actualEndDate;

    @OneToMany(mappedBy = "dailyReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DailyReportDetails> details = new ArrayList<DailyReportDetails>();

    public void addDetails(DailyReportDetails drd) {
	details.add(drd);
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the project
     */
    public Project getProject() {
	return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(Project project) {
	this.project = project;
    }

    /**
     * @return the reportedBy
     */
    public User getReportedBy() {
	return reportedBy;
    }

    /**
     * @param reportedBy
     *            the reportedBy to set
     */
    public void setReportedBy(User reportedBy) {
	this.reportedBy = reportedBy;
    }

    /**
     * @return the scopeWorkDesc
     */
    public String getScopeWorkDesc() {
	return scopeWorkDesc;
    }

    /**
     * @param scopeWorkDesc
     *            the scopeWorkDesc to set
     */
    public void setScopeWorkDesc(String scopeWorkDesc) {
	this.scopeWorkDesc = scopeWorkDesc;
    }

    /**
     * @return the sureTrackFile
     */
    public Filename getSureTrackFile() {
	return sureTrackFile;
    }

    /**
     * @param sureTrackFile
     *            the sureTrackFile to set
     */
    public void setSureTrackFile(Filename sureTrackFile) {
	this.sureTrackFile = sureTrackFile;
    }

    /**
     * @return the imageFile01
     */
    public Filename getImageFile01() {
	return imageFile01;
    }

    /**
     * @param imageFile01
     *            the imageFile01 to set
     */
    public void setImageFile01(Filename imageFile01) {
	this.imageFile01 = imageFile01;
    }

    /**
     * @return the imageFile02
     */
    public Filename getImageFile02() {
	return imageFile02;
    }

    /**
     * @param imageFile02
     *            the imageFile02 to set
     */
    public void setImageFile02(Filename imageFile02) {
	this.imageFile02 = imageFile02;
    }

    /**
     * @return the isWeatherDay
     */
    public String getIsWeatherDay() {
	return isWeatherDay;
    }

    /**
     * @param isWeatherDay
     *            the isWeatherDay to set
     */
    public void setIsWeatherDay(String isWeatherDay) {
	this.isWeatherDay = isWeatherDay;
    }

    /**
     * @return the actualEndDate
     */
    public Date getActualEndDate() {
	return actualEndDate;
    }

    /**
     * @param actualEndDate
     *            the actualEndDate to set
     */
    public void setActualEndDate(Date actualEndDate) {
	this.actualEndDate = actualEndDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "DailyReport [id=" + id + ", project=" + project
		+ ", reportedBy=" + reportedBy + ", scopeWorkDesc="
		+ scopeWorkDesc + ", sureTrackFile=" + sureTrackFile
		+ ", imageFile01=" + imageFile01 + ", imageFile02="
		+ imageFile02 + ", isWeatherDay=" + isWeatherDay
		+ ", actualEndDate=" + actualEndDate + "]";
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
	final DailyReport other = (DailyReport) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project, other.project);
    }

    /**
     * @return the details
     */
    public List<DailyReportDetails> getDetails() {
	return details;
    }

    /**
     * @param details
     *            the details to set
     */
    public void setDetails(List<DailyReportDetails> details) {
	this.details = details;
    }
}
