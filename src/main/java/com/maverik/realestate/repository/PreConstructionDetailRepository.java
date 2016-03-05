/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.PreConstructionTypeDetails;

/**
 * @author jorge
 *
 */
@Repository
public interface PreConstructionDetailRepository extends
	JpaRepository<PreConstructionTypeDetails, Long> {

}
