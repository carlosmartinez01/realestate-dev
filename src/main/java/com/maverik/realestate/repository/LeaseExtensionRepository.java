/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.LeaseExtension;
import com.maverik.realestate.domain.entity.PropertyLease;

/**
 * @author jorge
 *
 */
@Repository
public interface LeaseExtensionRepository extends
	JpaRepository<LeaseExtension, Long> {

    List<LeaseExtension> findByPropertyLeaseId(PropertyLease leaseId);
}
