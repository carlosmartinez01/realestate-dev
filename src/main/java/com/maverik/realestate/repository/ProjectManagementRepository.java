package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Project;
import com.maverik.realestate.domain.entity.ProjectManagement;

@Repository(value = "projectManagementRepository")
public interface ProjectManagementRepository extends
	JpaRepository<ProjectManagement, Long> {
    ProjectManagement findByProject(Project project);
}
