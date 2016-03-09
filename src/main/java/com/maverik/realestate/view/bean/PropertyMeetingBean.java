/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PropertyMeetingBean implements Serializable,
	Comparable<PropertyMeetingBean> {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 2440242201848455771L;

    private Long id;

    private Integer meetingId;

    private Long propertyPermittingId;

    @NotEmpty(message = "Do not leave empty the meeting name")
    private String meetingName;

    private String meeting;

    private String meetingSubDeadline;

    private String meetingDate;

    private String meetingApprovalDate;

    private String meetingNotes;

    public String getMeetingName() {
	return meetingName;
    }

    public void setMeetingName(String meetingName) {
	this.meetingName = meetingName;
    }

    public String getMeeting() {
	return meeting;
    }

    public void setMeeting(String meeting) {
	this.meeting = meeting;
    }

    public String getMeetingSubDeadline() {
	return meetingSubDeadline;
    }

    public void setMeetingSubDeadline(String meetingSubDeadline) {
	this.meetingSubDeadline = meetingSubDeadline;
    }

    public String getMeetingDate() {
	return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
	this.meetingDate = meetingDate;
    }

    public String getMeetingApprovalDate() {
	return meetingApprovalDate;
    }

    public void setMeetingApprovalDate(String meetingApprovalDate) {
	this.meetingApprovalDate = meetingApprovalDate;
    }

    public String getMeetingNotes() {
	return meetingNotes;
    }

    public void setMeetingNotes(String meetingNotes) {
	this.meetingNotes = meetingNotes;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.meetingId, this.propertyPermittingId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PropertyMeetingBean other = (PropertyMeetingBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.meetingId, this.meetingId)
		&& Objects.equals(this.propertyPermittingId,
			this.propertyPermittingId);
    }

    @Override
    public int compareTo(PropertyMeetingBean o) {
	// compareTo should return < 0 if this is supposed to be
	// less than other, > 0 if this is supposed to be greater than
	// other and 0 if they are supposed to be equal
	if (this.meetingId == o.meetingId) {
	    return 0;
	} else if (this.meetingId > o.meetingId) {
	    return 1;
	} else {
	    return -1;
	}
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Integer getMeetingId() {
	return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
	this.meetingId = meetingId;
    }

    public Long getPropertyPermittingId() {
	return propertyPermittingId;
    }

    public void setPropertyPermittingId(Long propertyPermittingId) {
	this.propertyPermittingId = propertyPermittingId;
    }

}
