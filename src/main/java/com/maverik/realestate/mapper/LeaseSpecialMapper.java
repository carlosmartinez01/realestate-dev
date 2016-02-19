/**
 * 
 */
package com.maverik.realestate.mapper;

import org.springframework.stereotype.Component;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PropertyLease;

/**
 * @author jorge
 *
 */
@Component
@SonarClassExclusion
public class LeaseSpecialMapper {

    public PropertyLease asPropertyLease(Long id) {
	if (id == null) {
	    return null;
	}

	PropertyLease pl = new PropertyLease();
	pl.setId(id);

	return pl;
    }

    public Long asLong(PropertyLease pl) {
	if (pl == null) {
	    return null;
	}

	return pl.getId();
    }

}
