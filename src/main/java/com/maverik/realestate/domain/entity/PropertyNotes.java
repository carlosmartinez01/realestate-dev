package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
@Entity
@Table(name = "property_notes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class PropertyNotes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTYNOTESID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NOTEID", nullable = false)
    private Note noteId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROPERTYID", nullable = false)
    private Property propertyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGEID", nullable = false)
    private Page pageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", nullable = false)
    private User userId;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    public Note getNoteId() {
	return noteId;
    }

    public void setNoteId(Note noteId) {
	this.noteId = noteId;
    }

    public Property getPropertyId() {
	return propertyId;
    }

    public void setPropertyId(Property propertyId) {
	this.propertyId = propertyId;
    }

    public Page getPageId() {
	return pageId;
    }

    public void setPageId(Page pageId) {
	this.pageId = pageId;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PropertyNotes other = (PropertyNotes) obj;
	return Objects.equals(this.id, other.id);
    }

    public User getUserId() {
	return userId;
    }

    public void setUserId(User userId) {
	this.userId = userId;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getCreationTime() {
	return creationTime;
    }
}
