package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
