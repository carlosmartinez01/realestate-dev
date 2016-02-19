/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyPurchase;

/**
 * @author jorge
 *
 */
public interface PropertyPurchaseRepository extends
	JpaRepository<PropertyPurchase, Long> {

    List<PropertyPurchase> findByPropertyIdAndAccepted(Property propertyId,
	    Byte accepted);

    @Modifying
    @Query(value = "UPDATE property_purchase set accepted = 0 where propertyPurchaseId in "
	    + "(select p.propertyPurchaseId from property_purchase p where p.propertyId = :propertyId and p.accepted = 1)", nativeQuery = true)
    Integer rejectPurchase(@Param("propertyId") Long propertyId);

}
