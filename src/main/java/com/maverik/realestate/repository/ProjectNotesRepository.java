/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Page;
import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectNotes;

/**
 * @author jorge
 *
 */
@Repository
public interface ProjectNotesRepository extends
	JpaRepository<ProjectNotes, Long> {

    List<ProjectNotes> findByProjectId(Project id);

    List<ProjectNotes> findByProjectIdAndPageId(Project projectId, Page pageId);

}
