/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PropertyNotes;
import com.maverik.realestate.view.bean.NoteBean;

/**
 * @author jorge
 *
 */

@SonarClassExclusion
@Component
public class PropertyNotesMapper {

    public List<NoteBean> asNoteBean(Set<PropertyNotes> propertyNotes) {
	if (propertyNotes == null) {
	    return new ArrayList<NoteBean>();
	}

	List<NoteBean> notes = new ArrayList<NoteBean>();
	for (PropertyNotes nr : propertyNotes) {
	    NoteBean n = new NoteBean();
	    n.setNoteId(nr.getNoteId().getNoteId());
	    n.setNotes(nr.getNoteId().getNotes());
	    n.setUser(nr.getUserId().getFirstName() + " "
		    + nr.getUserId().getLastName());
	    notes.add(n);
	}

	return notes;
    }
}
