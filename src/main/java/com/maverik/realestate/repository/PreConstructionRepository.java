/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.ProjectPreConstruction;

/**
 * @author jorge
 *
 */
public interface PreConstructionRepository extends
	JpaRepository<ProjectPreConstruction, Long> {

}
