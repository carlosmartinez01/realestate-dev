/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.PropertyMeeting;

/**
 * @author jorge
 *
 */
@Repository
public interface MeetingsRepository extends
	JpaRepository<PropertyMeeting, Long> {

}
