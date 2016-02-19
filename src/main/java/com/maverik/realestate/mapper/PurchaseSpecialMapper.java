/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PropertyPurchase;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Component
public class PurchaseSpecialMapper {

    public PropertyPurchase asPropertyPurchase(Long id) {
	if (id == null) {
	    return null;
	}

	PropertyPurchase pl = new PropertyPurchase();
	pl.setId(id);

	return pl;
    }

    public Long asLong(PropertyPurchase pl) {
	if (pl == null) {
	    return null;
	}

	return pl.getId();
    }

}
