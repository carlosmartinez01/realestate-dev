/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyLease;

/**
 * @author jorge
 *
 */
@Repository
public interface PropertyLeaseRepository extends
	JpaRepository<PropertyLease, Long> {

    List<PropertyLease> findByPropertyIdAndAccepted(Property propertyId,
	    Byte accepted);

    @Modifying
    @Query(value = "UPDATE property_lease set accepted = 0 where PROPERTYLEASEID in "
	    + "(select distinct PROPERTYLEASEID from property_lease where propertyId = :propertyId "
	    + "and accepted = 1)", nativeQuery = true)
    Integer rejectLease(@Param("propertyId") Long propertyId);
}
