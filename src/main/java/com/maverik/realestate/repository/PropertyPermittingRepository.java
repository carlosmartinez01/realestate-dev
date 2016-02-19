/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyPermitting;

/**
 * @author jorge
 *
 */
public interface PropertyPermittingRepository extends
	JpaRepository<PropertyPermitting, Long> {

    PropertyPermitting findByPropertyId(Property id);

}
