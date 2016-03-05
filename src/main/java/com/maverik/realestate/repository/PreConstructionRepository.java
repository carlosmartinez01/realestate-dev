/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;

/**
 * @author jorge
 *
 */
@Repository
public interface PreConstructionRepository extends
	JpaRepository<ProjectPreConstruction, Long> {

    ProjectPreConstruction findByProject(Project project);

}
