package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.Note;
import com.maverik.realestate.view.bean.NoteBean;

@Mapper(componentModel = "spring")
public interface NoteMapper {
	
	Note noteBeanToNote(NoteBean noteBean);
	
	NoteBean noteToNoteBean(Note note);
	
	List<Note> listOfNoteBeansToListOfNotes(List<NoteBean> noteBeans);
	
	List<NoteBean> listOfNotesToListOfNoteBeans(List<Note> notes);
}
