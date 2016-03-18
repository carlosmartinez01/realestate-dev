/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author jorge
 *
 */
public class DailyReportBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4800174718037546280L;

    private Long id;

    private Long project;

    private UserBean reportedBy;

    private String scopeWorkDesc;

    private FileBean sureTrackFile;

    private FileBean imageFile01;

    private FileBean imageFile02;

    private String isWeatherDay;

    private String actualEndDate;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "DailyReportBean [id=" + id + ", project=" + project
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
	final DailyReportBean other = (DailyReportBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.project, other.project);
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
    public Long getProject() {
	return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(Long project) {
	this.project = project;
    }

    /**
     * @return the reportedBy
     */
    public UserBean getReportedBy() {
	return reportedBy;
    }

    /**
     * @param reportedBy
     *            the reportedBy to set
     */
    public void setReportedBy(UserBean reportedBy) {
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
    public FileBean getSureTrackFile() {
	return sureTrackFile;
    }

    /**
     * @param sureTrackFile
     *            the sureTrackFile to set
     */
    public void setSureTrackFile(FileBean sureTrackFile) {
	this.sureTrackFile = sureTrackFile;
    }

    /**
     * @return the imageFile01
     */
    public FileBean getImageFile01() {
	return imageFile01;
    }

    /**
     * @param imageFile01
     *            the imageFile01 to set
     */
    public void setImageFile01(FileBean imageFile01) {
	this.imageFile01 = imageFile01;
    }

    /**
     * @return the imageFile02
     */
    public FileBean getImageFile02() {
	return imageFile02;
    }

    /**
     * @param imageFile02
     *            the imageFile02 to set
     */
    public void setImageFile02(FileBean imageFile02) {
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
    public String getActualEndDate() {
	return actualEndDate;
    }

    /**
     * @param actualEndDate
     *            the actualEndDate to set
     */
    public void setActualEndDate(String actualEndDate) {
	this.actualEndDate = actualEndDate;
    }

}
