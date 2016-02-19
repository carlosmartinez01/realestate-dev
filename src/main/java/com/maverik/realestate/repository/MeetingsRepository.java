/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.PropertyMeeting;

/**
 * @author jorge
 *
 */
public interface MeetingsRepository extends
	JpaRepository<PropertyMeeting, Long> {

}
