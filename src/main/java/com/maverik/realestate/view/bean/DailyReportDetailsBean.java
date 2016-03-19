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
public class DailyReportDetailsBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -520982677473582891L;

    private Long id;

    private Long dailyReport;

    private String company;

    private Integer people;

    private Double hours;

    private String workDescription;

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
     * @return the dailyReport
     */
    public Long getDailyReport() {
	return dailyReport;
    }

    /**
     * @param dailyReport
     *            the dailyReport to set
     */
    public void setDailyReport(Long dailyReport) {
	this.dailyReport = dailyReport;
    }

    /**
     * @return the company
     */
    public String getCompany() {
	return company;
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(String company) {
	this.company = company;
    }

    /**
     * @return the people
     */
    public Integer getPeople() {
	return people;
    }

    /**
     * @param people
     *            the people to set
     */
    public void setPeople(Integer people) {
	this.people = people;
    }

    /**
     * @return the hours
     */
    public Double getHours() {
	return hours;
    }

    /**
     * @param hours
     *            the hours to set
     */
    public void setHours(Double hours) {
	this.hours = hours;
    }

    /**
     * @return the workDescription
     */
    public String getWorkDescription() {
	return workDescription;
    }

    /**
     * @param workDescription
     *            the workDescription to set
     */
    public void setWorkDescription(String workDescription) {
	this.workDescription = workDescription;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "DailyReportDetailsBean [id=" + id + ", dailyReport="
		+ dailyReport + ", company=" + company + ", people=" + people
		+ ", hours=" + hours + ", workDescription=" + workDescription
		+ "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.dailyReport);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final DailyReportDetailsBean other = (DailyReportDetailsBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.dailyReport, other.dailyReport);
    }
}
