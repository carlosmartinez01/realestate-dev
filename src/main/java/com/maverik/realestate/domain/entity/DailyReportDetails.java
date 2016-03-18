/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@Entity
@Table(name = "daily_report_details")
@SonarClassExclusion
public class DailyReportDetails implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8808859003943557434L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dailyReportId", nullable = false)
    private DailyReport dailyReport;

    @Column(name = "company")
    private String company;

    @Column(name = "people")
    private Integer people;

    @Column(name = "hours")
    private Double hours;

    @Column(name = "workDescription")
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
    public DailyReport getDailyReport() {
	return dailyReport;
    }

    /**
     * @param dailyReport
     *            the dailyReport to set
     */
    public void setDailyReport(DailyReport dailyReport) {
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
	return "DailyReportDetails [id=" + id + ", dailyReport=" + dailyReport
		+ ", company=" + company + ", people=" + people + ", hours="
		+ hours + ", workDescription=" + workDescription + "]";
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
	final DailyReportDetails other = (DailyReportDetails) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.dailyReport, other.dailyReport);
    }
}
