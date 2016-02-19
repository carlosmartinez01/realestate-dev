package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.Page;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyNotes;

public interface PropertyNotesRepository extends
	JpaRepository<PropertyNotes, Long> {

    List<PropertyNotes> findByPropertyId(Property id);

    List<PropertyNotes> findByPropertyIdAndPageId(Property propertyId,
	    Page pageId);
}
