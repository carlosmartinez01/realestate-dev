package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
