/**
 * 
 */
package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.PropertyPurchase;
import com.maverik.realestate.domain.entity.PurchaseExtension;

/**
 * @author jorge
 *
 */
@Repository
public interface PurchaseExtensionRepository extends
	JpaRepository<PurchaseExtension, Long> {

    List<PurchaseExtension> findByPropertyPurchaseId(PropertyPurchase purchaseId);
}
