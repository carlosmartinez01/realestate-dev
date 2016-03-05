/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.PermittingAssignmentTask;
import com.maverik.realestate.domain.entity.Property;

/**
 * @author jorge
 *
 */
@Repository
public interface PermittingAssignmentTaskRepository extends
	JpaRepository<PermittingAssignmentTask, Long> {

    List<PermittingAssignmentTask> findByPropertyId(Property id);

}
