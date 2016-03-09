package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion(ignore = true)
public class NoteBean implements Serializable, Comparable<NoteBean> {

    private static final long serialVersionUID = 2754190197393458461L;

    private Long noteId;

    private String notes;

    private String user;

    private String formattedDate;

    public String getNotes() {
	return notes;
    }

    public void setNotes(String notes) {
	this.notes = notes;
    }

    public Long getNoteId() {
	return noteId;
    }

    public void setNoteId(Long noteId) {
	this.noteId = noteId;
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
	final NoteBean other = (NoteBean) obj;
	return Objects.equals(this.noteId, other.noteId);
    }

    @Override
    public String toString() {
	return "NoteBean [noteId=" + noteId + ", notes=" + notes + "]";
    }

    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(NoteBean o) {
	// compareTo should return < 0 if this is supposed to be
	// less than other, > 0 if this is supposed to be greater than
	// other and 0 if they are supposed to be equal
	if (this.noteId == o.noteId) {
	    return 0;
	} else if (this.noteId > o.noteId) {
	    return -1;
	} else {
	    return 1;
	}
    }

    public String getFormattedDate() {
	return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
	this.formattedDate = formattedDate;
    }
}
