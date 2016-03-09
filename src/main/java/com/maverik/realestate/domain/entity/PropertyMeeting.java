/**
 * 
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "property_meetings")
public class PropertyMeeting implements Serializable,
	Comparable<PropertyMeeting> {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 5779440569649145529L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "meetingId", nullable = false, insertable = true, updatable = false)
    private Integer meetingId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "propertyPermittingId")
    private PropertyPermitting propertyPermittingId;

    @Column(name = "meetingName", nullable = false)
    private String meetingName;

    @Column(name = "meeting", nullable = false)
    private String meeting;

    @Column(name = "meetingSubDeadline", nullable = false)
    private Date meetingSubDeadline;

    @Column(name = "meetingDate", nullable = false)
    private Date meetingDate;

    @Column(name = "meetingApprovalDate", nullable = false)
    private Date meetingApprovalDate;

    @Column(name = "meetingNotes", nullable = false)
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

    public Date getMeetingSubDeadline() {
	return meetingSubDeadline;
    }

    public void setMeetingSubDeadline(Date meetingSubDeadline) {
	this.meetingSubDeadline = meetingSubDeadline;
    }

    public Date getMeetingDate() {
	return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
	this.meetingDate = meetingDate;
    }

    public Date getMeetingApprovalDate() {
	return meetingApprovalDate;
    }

    public void setMeetingApprovalDate(Date meetingApprovalDate) {
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
	final PropertyMeeting other = (PropertyMeeting) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.meetingId, this.meetingId)
		&& Objects.equals(this.propertyPermittingId,
			this.propertyPermittingId);
    }

    @Override
    public int compareTo(PropertyMeeting o) {
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

    public PropertyPermitting getPropertyPermittingId() {
	return propertyPermittingId;
    }

    public void setPropertyPermittingId(PropertyPermitting propertyPermittingId) {
	this.propertyPermittingId = propertyPermittingId;
    }
}
