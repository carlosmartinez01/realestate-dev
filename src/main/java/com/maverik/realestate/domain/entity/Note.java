package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
@Entity
@Table(name = "notes")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTEID", nullable = false, updatable = false, insertable = false)
    private Long noteId;

    @Column(name = "NOTES", nullable = true, length = 8000)
    private String notes;

    @OneToOne(mappedBy = "noteId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PropertyNotes propertyNotes;

    @OneToOne(mappedBy = "noteId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProjectNotes projectNotes;

    public Long getNoteId() {
	return noteId;
    }

    public void setNoteId(Long noteId) {
	this.noteId = noteId;
    }

    public String getNotes() {
	return notes;
    }

    public void setNotes(String notes) {
	this.notes = notes;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.noteId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final Note other = (Note) obj;
	return Objects.equals(this.noteId, other.noteId);
    }

    @Override
    public String toString() {
	return "Note [noteId=" + noteId + ", notes=" + notes + "]";
    }

    public PropertyNotes getPropertyNotes() {
	return propertyNotes;
    }

    public void setPropertyNotes(PropertyNotes propertyNotes) {
	this.propertyNotes = propertyNotes;
    }

    public ProjectNotes getProjectNotes() {
	return projectNotes;
    }

    public void setProjectNotes(ProjectNotes projectNotes) {
	this.projectNotes = projectNotes;
    }
}
