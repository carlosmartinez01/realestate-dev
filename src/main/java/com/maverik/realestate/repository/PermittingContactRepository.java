/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.PermittingContact;
import com.maverik.realestate.domain.entity.Property;

/**
 * @author jorge
 *
 */
@Repository
public interface PermittingContactRepository extends
	JpaRepository<PermittingContact, Long> {

    List<PermittingContact> findByPropertyId(Property id);
}
