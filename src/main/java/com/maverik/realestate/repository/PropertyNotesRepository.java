package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Page;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyNotes;

@Repository
public interface PropertyNotesRepository extends
	JpaRepository<PropertyNotes, Long> {

    List<PropertyNotes> findByPropertyId(Property id);

    List<PropertyNotes> findByPropertyIdAndPageId(Property propertyId,
	    Page pageId);
}
