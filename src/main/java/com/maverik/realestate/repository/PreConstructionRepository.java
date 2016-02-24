/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectPreConstruction;

/**
 * @author jorge
 *
 */
public interface PreConstructionRepository extends
	JpaRepository<ProjectPreConstruction, Long> {

    List<ProjectPreConstruction> findAllByProject(Project project);

}
