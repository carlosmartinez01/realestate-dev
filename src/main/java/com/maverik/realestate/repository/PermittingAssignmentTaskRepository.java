/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.PermittingAssignmentTask;
import com.maverik.realestate.domain.entity.Property;

/**
 * @author jorge
 *
 */
public interface PermittingAssignmentTaskRepository extends
	JpaRepository<PermittingAssignmentTask, Long> {

    List<PermittingAssignmentTask> findByPropertyId(Property id);

}
