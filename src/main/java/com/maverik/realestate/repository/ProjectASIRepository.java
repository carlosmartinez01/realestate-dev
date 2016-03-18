/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectASI;

/**
 * @author jorge
 *
 */
@Repository(value = "projectASIRepository")
public interface ProjectASIRepository extends JpaRepository<ProjectASI, Long> {

    ProjectASI findByProject(Project project);
}
