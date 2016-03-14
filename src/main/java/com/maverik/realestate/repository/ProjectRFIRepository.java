/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectRFI;

/**
 * @author jorge
 *
 */
@Repository(value = "projectRFIRepository")
public interface ProjectRFIRepository extends JpaRepository<ProjectRFI, Long> {

    ProjectRFI findByProject(Project project);
}
