/**
 * 
 */
package com.maverik.realestate.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyLOI;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Repository(value = "propertyLOIRepository")
public interface PropertyLOIRepository extends JpaRepository<PropertyLOI, Long> {

    @Modifying
    @Query(value = "Insert into PropertyLOI (propertyId, price, accepted) values(:propertyId, :price, :accepted)", nativeQuery = true)
    PropertyLOI savePropertyLOI(@Param("propertyId") Long propertyId,
	    @Param("price") BigDecimal price, @Param("accepted") Byte accepted);

    @Modifying
    @Query(value = "UPDATE PropertyLOI set price = :price where id = :propertyLOIID")
    Integer updatePrice(@Param("price") BigDecimal price,
	    @Param("propertyLOIID") Long propertyLOIID);

    @Modifying
    @Query(value = "UPDATE PropertyLOI set accepted = 1, price = :price where id = :propertyLOIID")
    Integer acceptPrice(@Param("price") BigDecimal price,
	    @Param("propertyLOIID") Long propertyLOIID);

    @Modifying
    @Query(value = "UPDATE Property_LOI set accepted = 0 where propertyLOIId in"
	    + "(select propertyLOIId from property_loi where propertyId = :propertyId and accepted = 1)", nativeQuery = true)
    Integer rejectPrice(@Param("propertyId") Long propertyId);

    List<PropertyLOI> findByPropertyId(Property propertyId);

    List<PropertyLOI> findByPropertyIdAndAccepted(Property propertyId,
	    Byte accepted);

}
